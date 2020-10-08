# 统一异常处理
针对异常处理，我们原来的做法是一般在最外层捕获异常即可，例如在Controller中。这样的话也能解决部分问题，但是无法获取到自己指定的异常，引入全局统一异常处理的话将会极大的改善代码，减少冗余代码的产生。

## Java异常结构示意图

```
graph TB
Throwable-->Error
Error-->VirtualMechineError
Error-->..
VirtualMechineError-->StackOverflowError
VirtualMechineError-->OutOfMemoryError
Throwable-->Exception
Exception-->IOEception
Exception-->...
Exception-->SQLEception
Exception-->RuntimeException
RuntimeException-->NullPointerException
RuntimeException-->....
RuntimeException-->ArithmeticException
RuntimeException-->ArrayIndexOutOfBoundsException
```
## 基于Springboot统一异常处理
在web开发中发生了异常，采用统一的异常处理，来保证客户端能够收到友好的提示。
### 自定义异常
可根据需求自定义不同类型异常。
```
public class CheckedException extends Exception{

    private int code = 0;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(int code, String message) {
        super(message);
        this.setCode(code);
    }

}

public class UncheckedException extends RuntimeException{

    private int code = 0;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UncheckedException(String message) {
        super(message);
    }

    public UncheckedException(int code, String message) {
        super(message);
        this.setCode(code);
    }
}
```

### 基于@ControllerAdvice注解
基于@ControllerAdvice注解的全局异常统一处理只能针对于Controller层的异常，service层或者其他层面的异常都不能捕获。@ExceptionHandler(value = Exception.class)这个注解写在方法上表示,该方法处理哪个异常。

```
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({CheckedException.class,})
    public HttpResult handException(CheckedException e) {
        log.error(e.getMessage());
        log.debug("ExceptionControllerAdvice: CheckedException异常信息:", e);
        return HttpResult.response(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({UncheckedException.class})
    public HttpResult handException(UncheckedException e) {
        log.error(e.getMessage());
        log.debug("ExceptionControllerAdvice: UncheckedException异常信息:", e);
        return HttpResult.response(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class,})
    public HttpResult handException(Exception e) {
        log.error(e.getMessage());
        log.debug("ExceptionControllerAdvice: Exception异常信息:", e);
        return HttpResult.response(HttpResult.ERROR_CODE, e.getMessage(), null);
    }
}
```

### 实现ErrorController接口
Spring Boot提供了一个默认的映射：/error。当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容。基于Springboot自身的全局异常统一处理，主要是实现ErrorController接口或者继承AbstractErrorController抽象类或者继承BasicErrorController类

```
@Controller
public class PageErrorHandler implements ErrorController {
 
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 500) {
            return "/WEB-INF/error/500";
        } else if (statusCode == 404) {
            return "/WEB-INF/error/404";
        } else if (statusCode == 403) {
            return "/WEB-INF/error/403";
        } else {
            return "/WEB-INF/error/500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
```


## 异常使用参考
- 异常避免用于业务逻辑判断；
- 异常可以按需转换成更好理解的异常抛出；
- 单线程同步执行的情况下，直接抛出异常或捕捉异常后做一些类似记录日志的操作后包装异常再抛出；
- 在统一应用层捕捉处理异常，并给浏览器响应一个友好的错误提示信息；
- 特殊场景异常需要补偿处理让程序继续执行的可记录异常到日志；
- 对异常进行文档说明；
- 对抛出的异常进行明确的说明&包装异常时不要抛弃原始异常；
- 使用标准Java API提供的异常,若不满足要求，可创建自己的定制异常。
- 不要记录并抛出异常；
- 不要使用异常控制程序的流程；
- 不要直接catch大段代码，并抛出大异常；
- 在调用RPC、二方包、或动态生成类的相关方法时，捕捉异常必须使用Throwable类来进行拦截；

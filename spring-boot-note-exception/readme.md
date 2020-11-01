# API数据返回和异常处理

## 基于@ControllerAdvice注解
基于@ControllerAdvice注解的全局异常统一处理只能针对于Controller层的异常，service层或者其他层面的异常都不能捕获。@ExceptionHandler(value = Exception.class)这个注解写在方法上表示,该方法处理哪个异常。

## 自定义注解+ResponseBodyAdvice<Object>实现返回拦截
```
@RestControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    private static final Logger logger = LoggerFactory.getLogger(ResponseResultHandler.class);

    /**
     * 这里使用自定义注解
     * 也可以使用ResponseBody.class进行标记
     */
    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResult.class;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof Result) {
            return o;
        } else if (o instanceof String) {
            return JSON.toJSONString(Result.success(o));
        } else {
            return Result.success(o);
        }
    }

    @ExceptionHandler({CheckedException.class,})
    public Result handException(CheckedException e) {
        logger.error("ExceptionControllerAdvice: CheckedException异常信息:", e);
        return Result.error(e.getResultCode(), e.getMessage());
    }

    @ExceptionHandler({UncheckedException.class})
    public Result handException(UncheckedException e) {
        logger.error("ExceptionControllerAdvice: UncheckedException异常信息:", e);
        return Result.error(e.getResultCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class,})
    public Result handException(Exception e) {
        logger.error("ExceptionControllerAdvice: Exception异常信息:", e);
        return Result.error(ResultCode.ERROR, e.getMessage());
    }

}
```

## 实现ErrorController接口
Spring Boot提供了一个默认的映射：/error。当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容。基于Springboot自身的全局异常统一处理，主要是实现ErrorController接口或者继承AbstractErrorController抽象类或者继承BasicErrorController类

```
@Controller
public class ControllerErrorHandler implements ErrorController {
 
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


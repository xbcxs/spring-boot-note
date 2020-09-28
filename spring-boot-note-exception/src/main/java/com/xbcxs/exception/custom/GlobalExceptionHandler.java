package com.xbcxs.exception.custom;

import com.xbcxs.exception.exception.CheckedException;
import com.xbcxs.exception.exception.UncheckedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 拦截Controller自定义异常并进行处理
 * PS：无法拦截Interceptor异常
 *
 * @author Xiao
 * @date 2020/9/28
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({CheckedException.class,})
    public HttpResult handException(CheckedException e) {
        log.error(e.getMessage());
        log.debug("ExceptionControllerAdvice: CheckedException异常信息:", e);
        return HttpResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({UncheckedException.class})
    public HttpResult handException(UncheckedException e) {
        log.error(e.getMessage());
        log.debug("ExceptionControllerAdvice: UncheckedException异常信息:", e);
        return HttpResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class,})
    public HttpResult handException(Exception e) {
        log.error(e.getMessage());
        log.debug("ExceptionControllerAdvice: Exception异常信息:", e);
        return HttpResult.error(0, e.getMessage());
    }
}

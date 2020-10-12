package com.xbcxs.exception.handler;

import com.xbcxs.exception.common.HttpResult;
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
 */
@ResponseBody
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({CheckedException.class,})
    public HttpResult handException(CheckedException e) {
        logger.error(e.getMessage());
        logger.debug("ExceptionControllerAdvice: CheckedException异常信息:", e);
        return HttpResult.response(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({UncheckedException.class})
    public HttpResult handException(UncheckedException e) {
        logger.error(e.getMessage());
        logger.debug("ExceptionControllerAdvice: UncheckedException异常信息:", e);
        return HttpResult.response(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class,})
    public HttpResult handException(Exception e) {
        logger.error(e.getMessage());
        logger.debug("ExceptionControllerAdvice: Exception异常信息:", e);
        return HttpResult.response(HttpResult.ERROR_CODE, e.getMessage());
    }
}

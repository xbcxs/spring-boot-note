package com.xbcxs.exception.response;

import com.alibaba.fastjson.JSON;
import com.xbcxs.exception.exception.CheckedException;
import com.xbcxs.exception.exception.UncheckedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * <p>RestControllerAdvice: 拦截Controller自定义异常并进行处理
 *
 * <p>ResponseBodyAdvice<Object>: 拦截Controller方法默认返回参数，统一处理返回值、响应体
 *
 * @author Xiao
 */
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

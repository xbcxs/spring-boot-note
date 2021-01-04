package com.xbcxs.exception.handler;

import com.xbcxs.exception.exception.NotFoundException;
import com.xbcxs.exception.exception.NotImplementedException;
import com.xbcxs.exception.model.ResponseResultExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

/**
 * <p>@RestControllerAdvice: 拦截Controller自定义异常并进行处理
 *
 * <p>ResponseBodyAdvice<Object>: 拦截Controller方法默认返回参数，统一处理返回值、响应体
 *
 * @author Xiao
 */
@RestControllerAdvice
public class ResponseResultHandler /*implements ResponseBodyAdvice<Object>*/ {

    private static final Logger logger = LoggerFactory.getLogger(ResponseResultHandler.class);

    /**
     * 这里使用自定义注解
     * 也可以使用ResponseBody.class进行标记
     */
    /*private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultWrap.class;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ResponseResult) {
            return o;
        } else if (o instanceof String) {
            return JSON.toJSONString(ResponseResult.success(o));
        } else {
            return ResponseResult.success(o);
        }
    }*/

    /**
     * ExceptionHandler.value以外未拦截到的异常(Error)等将由HTTP Status Code处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseResultExt handleCustomException(Exception ex) {
        HttpStatus status;
        if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof ValidationException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof NotImplementedException) {
            status = HttpStatus.NOT_IMPLEMENTED;
        } else {
            logger.warn("Unknown exception type: {}" + ex.getClass().getName(), ex);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseResultExt(status.getReasonPhrase(), String.valueOf(status.value()), ex.getMessage(), "", ex);
    }

}

package com.xbcxs.exception.custom;

import javax.servlet.http.HttpServletResponse;

/**
 * http响应结果
 *
 * @author Xiao
 * @date 2020/9/28
 */
public class HttpResult<T> {

    private int code;
    private String message;
    private T data;

    /**
     * 默认错误码
     */
    private final static int ERROR_CODE = 0;

    /**
     * 默认成功提示
     */
    private final static String DEFAULT_SUCCESS = "成功！";

    private HttpResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> HttpResult success() {
        return new HttpResult(HttpServletResponse.SC_OK, DEFAULT_SUCCESS, "");
    }

    public static <T> HttpResult success(T data) {
        return new HttpResult(HttpServletResponse.SC_OK, DEFAULT_SUCCESS, data);
    }

    public static <T> HttpResult success(String message, T data) {
        return new HttpResult(HttpServletResponse.SC_OK, message, data);
    }

    public static HttpResult error(String message) {
        return new HttpResult(ERROR_CODE, message, "");
    }

    public static HttpResult error(int code, String message) {
        return new HttpResult(code, message, "");
    }
}

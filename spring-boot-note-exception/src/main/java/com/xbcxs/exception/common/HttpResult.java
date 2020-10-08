package com.xbcxs.exception.common;

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
    public final static int ERROR_CODE = 0;

    /**
     * 默成功误码
     */
    public final static int SUCCESS_CODE = 1;

    /**
     * 默认响应提示
     */
    private final static String SUCCESS_MESSAGE = "完成！";

    private HttpResult(int code, String message, T data) {
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

    public static <T> HttpResult response() {
        return new HttpResult(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static <T> HttpResult response(T data) {
        return new HttpResult(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> HttpResult response(int code, String message) {
        return new HttpResult(code, message, null);
    }

    public static <T> HttpResult response(int code, String message, T data) {
        return new HttpResult(code, message, data);
    }

}

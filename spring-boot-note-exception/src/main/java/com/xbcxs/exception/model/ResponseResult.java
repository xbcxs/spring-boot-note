package com.xbcxs.exception.model;

/**
 * http响应结果
 */
public class ResponseResult<T> {

    private String code;
    private String message;
    private T data;

    public ResponseResult() {

    }

    private ResponseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public static ResponseResult success() {
        return new ResponseResult("0", "success", null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult("0", "success", data);
    }

    public static ResponseResult error() {
        return new ResponseResult("1", "error", null);
    }

    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult("1", message, null);
    }

}

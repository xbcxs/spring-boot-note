package com.xbcxs.exception.model;

/**
 * http响应结果
 *
 * @author Xiao
 */
public class ResponseResult<T> {

    private String code;
    private String message;
    private T data;

    public ResponseResult() {

    }

    private ResponseResult(ResponseResultCode resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
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
        return new ResponseResult(ResponseResultCode.SUCCESS, null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult(ResponseResultCode.SUCCESS, data);
    }

    public static ResponseResult failure() {
        return new ResponseResult(ResponseResultCode.ERROR, null);
    }

    public static <T> ResponseResult<T> failure(String message) {
        return new ResponseResult(ResponseResultCode.ERROR.getCode(), message, null);
    }

}

package com.xbcxs.exception.result;

/**
 * http响应结果
 *
 * @author Xiao
 */
public class Result<T> {

    private String code;
    private String message;
    private T data;

    private Result(ResultCode resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
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

    public static Result success() {
        return new Result(ResultCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    public static Result error() {
        return new Result(ResultCode.ERROR, null);
    }

    public static <T> Result<T> error(T data) {
        return new Result(ResultCode.ERROR, data);
    }

    public static <T> Result<T> error(ResultCode resultStatus, T data) {
        if (resultStatus == null) {
            return error(data);
        }
        return new Result(resultStatus, data);
    }

}

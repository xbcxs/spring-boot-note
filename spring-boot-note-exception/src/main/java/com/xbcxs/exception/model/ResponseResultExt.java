package com.xbcxs.exception.model;

public class ResponseResultExt extends ResponseResult {

    private String status;
    private Throwable throwable;

    public ResponseResultExt(String status, String code, String message, Object data, Throwable throwable) {
        this.status = status;
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
        this.throwable = throwable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
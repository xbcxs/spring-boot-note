package com.xbcxs.exception.exception;

/**
 * 不受检测异常
 *
 * @author Xiao
 */
public class UncheckedException extends RuntimeException{

    /**
     * 默认错误码
     */
    private int code = 0;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UncheckedException(String message) {
        super(message);
    }

    public UncheckedException(int code, String message) {
        super(message);
        this.setCode(code);
    }
}

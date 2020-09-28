package com.xbcxs.exception.exception;

/**
 * 受检测异常
 *
 * @author Xiao
 * @date 2020/9/28
 */
public class CheckedException extends Exception{

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

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(int code, String message) {
        super(message);
        this.setCode(code);
    }

}

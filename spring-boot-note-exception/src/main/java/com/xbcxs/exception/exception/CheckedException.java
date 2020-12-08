package com.xbcxs.exception.exception;

/**
 * 受检测异常
 *
 * @author Xiao
 */
public class CheckedException extends Exception {

    public CheckedException() {
        super();
    }

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

}

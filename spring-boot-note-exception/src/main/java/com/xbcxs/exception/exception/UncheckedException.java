package com.xbcxs.exception.exception;

/**
 * 不受检测异常
 *
 * @author Xiao
 */
public class UncheckedException extends RuntimeException {
    public UncheckedException() {
        super();
    }

    public UncheckedException(String message) {
        super(message);
    }

    public UncheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncheckedException(Throwable cause) {
        super(cause);
    }


}

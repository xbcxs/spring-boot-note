package com.xbcxs.exception.exception;

import com.xbcxs.exception.result.ResultCode;

/**
 * 不受检测异常
 *
 * @author Xiao
 */
public class UncheckedException extends RuntimeException{

    /**
     * 默认错误码
     */
    private ResultCode resultCode = ResultCode.ERROR;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public UncheckedException(String message) {
        super(message);
    }

    public UncheckedException(ResultCode resultCode, String message) {
        super(message);
        this.setResultCode(resultCode);
    }
}

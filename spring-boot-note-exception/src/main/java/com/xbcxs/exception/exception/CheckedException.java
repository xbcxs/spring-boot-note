package com.xbcxs.exception.exception;

import com.xbcxs.exception.result.ResultCode;

/**
 * 受检测异常
 *
 * @author Xiao
 */
public class CheckedException extends Exception{

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

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(ResultCode resultCode, String message) {
        super(message);
        this.setResultCode(resultCode);
    }

}

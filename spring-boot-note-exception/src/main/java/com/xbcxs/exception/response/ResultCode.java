package com.xbcxs.exception.response;

/**
 * HTTP请求结果状态码，参考阿里JAVA开发手册
 *
 * @author Xiao
 */
public enum ResultCode {

    /** 一切OK */
    SUCCESS("00000", "OK"),
    /** 宏观错误 */
    ERROR("00001", "Error"),
    /** 一级宏观错误 */
    CLIENT_ERROR("A0001", "用户端错误"),
    SYSTEM_EXECUTE_ERROR("B0001", "系统执行出错"),
    CALL_THIRD_SERVER_ERROR ("C0001", "调用第三方服务出错"),
    ;

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
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
}

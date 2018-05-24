package com.lechinepay.framework.exception;

public class UnCheckedException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 3114462919560925588L;

    private String            code;

    public UnCheckedException(String code) {
        this.code = code;
    }

    public UnCheckedException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

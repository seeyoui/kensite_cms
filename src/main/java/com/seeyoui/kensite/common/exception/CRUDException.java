package com.seeyoui.kensite.common.exception;

/**
 * 说明：数据操作自定义异常类 <br>
 */
public class CRUDException extends RuntimeException {

    private static final long serialVersionUID = 4570573798200354363L;

    public CRUDException(String msg) {
        super(msg);
    }

    public CRUDException(Throwable cause) {
        super(cause);
    }

    public CRUDException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

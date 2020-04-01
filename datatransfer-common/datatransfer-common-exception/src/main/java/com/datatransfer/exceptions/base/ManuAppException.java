package com.datatransfer.exceptions.base;
/**
 * 应用异常类
 * @author  panxiaobo
 * date 2019/8/1 15:44
 */

public class ManuAppException extends RuntimeException {

    public ManuAppException(String msg)
    {
        super(msg);
    }

    public ManuAppException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public ManuAppException()
    {
        super("not implements: manuapp");
    }
}

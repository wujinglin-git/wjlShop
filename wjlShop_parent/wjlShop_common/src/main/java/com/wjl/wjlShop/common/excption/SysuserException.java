package com.wjl.wjlShop.common.excption;

/**
 * @Author: wugege
 * @Date: 2019/9/22 9:44
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public class SysuserException extends Exception {
    public SysuserException() {
        super();
    }

    public SysuserException(String message) {
        super(message);
    }

    public SysuserException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysuserException(Throwable cause) {
        super(cause);
    }

    protected SysuserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

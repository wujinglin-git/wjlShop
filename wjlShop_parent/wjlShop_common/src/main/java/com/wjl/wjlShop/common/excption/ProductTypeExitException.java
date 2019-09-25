package com.wjl.wjlShop.common.excption;

/**
 * @Author: wugege
 * @Date: 2019/9/4 21:53
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public class ProductTypeExitException extends Exception {
    public ProductTypeExitException() {
        super();
    }

    public ProductTypeExitException(String message) {
        super(message);
    }

    public ProductTypeExitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTypeExitException(Throwable cause) {
        super(cause);
    }

    protected ProductTypeExitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

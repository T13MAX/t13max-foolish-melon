package com.t13max.common.exception;

/**
 * 数据异常
 *
 * @author: t13max
 * @since: 14:11 2024/5/23
 */
public class DataException extends RuntimeException {

    public DataException(String message) {
        super(message);
    }

    public DataException(Throwable cause) {
        super(cause);
    }
}

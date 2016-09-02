package com.black.spring.retrofit.exception;

/**
 * @Author : BlackShadowWalker
 * @Date : 2016-09-02
 */
public class RetrofitRpcInvockException extends RuntimeException {
    public RetrofitRpcInvockException() {
    }

    public RetrofitRpcInvockException(String message) {
        super(message);
    }

    public RetrofitRpcInvockException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetrofitRpcInvockException(Throwable cause) {
        super(cause);
    }
}

package com.github.blackshadowwalker.spring.retrofit.bean;

/**
 * Created by ASUS on 9/2 0002.
 */
public class ResponseDto<T> {

    int code;
    String msg;
    T data;

    public ResponseDto() {
    }

    public ResponseDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseDto(T data) {
        this.data = data;
        this.code = 200;
        this.msg = "Success";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

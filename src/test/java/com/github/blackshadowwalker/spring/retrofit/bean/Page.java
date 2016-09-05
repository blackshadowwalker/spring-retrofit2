package com.github.blackshadowwalker.spring.retrofit.bean;

/**
 * Created by ASUS on 9/2 0002.
 */
public class Page<T> {

    int pageNum;
    int pageSize;
    T result;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

package com.gobue.blink.order.api.constant;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final String SUCCESS = "SUCCESS";
    private static final String ERROR = "ERROR";

    protected String code;
    protected String msg;
    protected T t;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public boolean isSuccess() {
        return SUCCESS.equals(code);
    }
}

package com.xpl.framework;

import com.alibaba.fastjson.JSON;

public class ResultView<T> {

    private int code;
    private String msg;
    private T data;

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

    public ResultView(){

    }

    public ResultView(int code){
        this.code = code;
    }

    public ResultView(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultView(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

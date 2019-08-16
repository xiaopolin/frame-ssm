package com.xpl.framework;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

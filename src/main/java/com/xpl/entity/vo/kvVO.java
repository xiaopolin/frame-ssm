package com.xpl.entity.vo;

import com.xpl.framework.BaseEntity;

public class kvVO extends BaseEntity {

    private int k;  //key
    private String v;  //value

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}

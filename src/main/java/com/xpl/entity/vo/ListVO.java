package com.xpl.entity.vo;

import com.xpl.framework.BaseEntity;

import java.util.List;

public class ListVO<T> extends BaseEntity {

    private List<T> datas;
    private int count;

    public List<T> getData() {
        return datas;
    }

    public void setData(List<T> data) {
        this.datas = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

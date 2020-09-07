package com.zx.bo;

import com.zx.pojo.Goods;


public class GoodsBiz extends Goods {
    private String tname;

    public GoodsBiz() {
    }

    public GoodsBiz(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "GoodsBiz{" +
                "tname='" + tname + '\'' +
                "} " + super.toString();
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}

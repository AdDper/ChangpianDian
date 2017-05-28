package com.zilong.demo.changpiandian.model;

import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/22.
 */

public class OrderInfo {
    private int order_image;
    private String order_albumName;
    private int count;
    private int pay;

    public int getOrder_image() {
        return order_image;
    }

    public void setOrder_image(int order_image) {
        this.order_image = order_image;
    }

    public String getOrder_albumName() {
        return order_albumName;
    }

    public void setOrder_albumName(String order_albumName) {
        this.order_albumName = order_albumName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}

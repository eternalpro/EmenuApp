package com.yuansewenhua.dto;

import java.util.Comparator;

/**
 * Created by YangJD on 2014/12/23.
 * 用于生成订单
 */
public class GoodsForOrder{
    private Long id;
    private String name;
    private String price;
    private String smallimagepath;
    private String bigimagepath;
    private String type;
    private boolean isenable;
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSmallimagepath() {
        return smallimagepath;
    }

    public void setSmallimagepath(String smallimagepath) {
        this.smallimagepath = smallimagepath;
    }

    public String getBigimagepath() {
        return bigimagepath;
    }

    public void setBigimagepath(String bigimagepath) {
        this.bigimagepath = bigimagepath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsenable() {
        return isenable;
    }

    public void setIsenable(boolean isenable) {
        this.isenable = isenable;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

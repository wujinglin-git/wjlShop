package com.wjl.wjlShop.pojo;

import java.io.Serializable;

/**
 * @Author: wugege
 * @Date: 2019/8/27 20:44
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public class ProductType implements Serializable {
    //不是分布式 应该可以不进行序列化试试？
    private Integer id;
    private  String name;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

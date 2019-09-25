package com.wjl.wjlShop.pojo;

/**
 * @Author: wugege
 * @Date: 2019/9/9 21:34
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public class Product {
    private  Integer id;
    private  String name;
    private  Double price;
    private  String info;     //简介

    private  String image;    //图片
    private  ProductType productType;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}

package com.wjl.wjlShop.dto;

import java.io.InputStream;

/**
 * @Author: wugege
 * @Date: 2019/9/9 8:31
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public class ProductDto {
    private  Integer id ;
    private String name;
    private  Double price;
    private Integer productTypeId;
    private InputStream inputStream;
    private String fileName;
    private String uploadPath;

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

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

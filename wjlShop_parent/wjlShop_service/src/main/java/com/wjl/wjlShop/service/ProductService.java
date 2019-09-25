package com.wjl.wjlShop.service;

import com.wjl.wjlShop.dto.ProductDto;
import com.wjl.wjlShop.pojo.Product;
import org.apache.commons.fileupload.FileUploadException;

import java.io.OutputStream;
import java.util.List;

/**
 * @Author: wugege
 * @Date: 2019/9/8 21:27
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public interface ProductService {
    public void add(ProductDto productDto) throws FileUploadException;

    public boolean checkName(String name);

    public List<Product> findAll();

    public void remove(Integer id);

    public Product findById(Integer id);

    public void findImage(String path, OutputStream outputStream);

    public void modify(ProductDto productDto) throws FileUploadException;

    public void deleteById(Integer id);

}

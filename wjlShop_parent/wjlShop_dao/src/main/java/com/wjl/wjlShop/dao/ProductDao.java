package com.wjl.wjlShop.dao;

import com.wjl.wjlShop.pojo.Product;

import java.util.List;
import java.util.Map;

/**
 * @Author: wugege
 * @Date: 2019/9/9 21:33
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public interface ProductDao {

    public void insert(Product product);

    public Product selectByName(String name);

    public Product selectById(Integer id);

    public List<Product> selectAll();

    public void update(Product product);

    public void deleteById(Integer id);

    public void updateButImage(Product product);



}

package com.wjl.wjlShop.dao;


import com.wjl.wjlShop.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wugege
 * @Date: 2019/9/1 15:17
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public interface ProductTypeDao {

    public List<ProductType> selectAll();

    public ProductType findById(Integer id);

    public  ProductType findByName(String name);
    /*
    * 插入
    * */
    public void insert(@Param("name")String name,@Param("status")Integer status);
    /*
    * 更新name
    * */
    public void updateName(@Param("id") Integer id, @Param("name")String name);

    public void updateStatus(@Param("id")Integer id,@Param("status")Integer status);

    public void deleteById(Integer id);

    /*
    * 查找有效的种类
    * */
    public List<ProductType> selectByStatus(Integer status);

}

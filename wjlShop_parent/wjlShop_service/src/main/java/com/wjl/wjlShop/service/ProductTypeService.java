package com.wjl.wjlShop.service;


import com.github.pagehelper.PageInfo;
import com.wjl.wjlShop.common.constant.ProductTypeConstant;
import com.wjl.wjlShop.common.excption.ProductTypeExitException;
import com.wjl.wjlShop.pojo.ProductType;

import java.util.List;

/**
 * @Author: wugege
 * @Date: 2019/9/1 16:15
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public interface ProductTypeService {

    public List<ProductType> findAll();
    /*
    * 查找有效的
    * */
    public  List<ProductType> findEnable();

    public void addProductType(String name) throws ProductTypeExitException;

    public ProductType findById(Integer id);

    public void modifyName(Integer id , String name) throws ProductTypeExitException;

    public void modifyStatus(Integer id);

    public void deleteName(Integer id);


}

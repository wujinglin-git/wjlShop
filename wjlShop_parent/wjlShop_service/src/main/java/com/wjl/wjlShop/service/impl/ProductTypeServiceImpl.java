package com.wjl.wjlShop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjl.wjlShop.common.constant.ProductTypeConstant;
import com.wjl.wjlShop.common.excption.ProductTypeExitException;
import com.wjl.wjlShop.dao.ProductTypeDao;
import com.wjl.wjlShop.pojo.ProductType;
import com.wjl.wjlShop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wugege
 * @Date: 2019/9/1 16:16
 * 一给窝哩 giao giao 呀吼
 * 4
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    ProductTypeDao productTypeDao;
    @Transactional(readOnly = true)
    @Override
    public List<ProductType> findAll() {
        return productTypeDao.selectAll();
    }
    /*
    * 查找启用的商品类型
    * */
    @Override
    public List<ProductType> findEnable() {
        List<ProductType> list = productTypeDao.selectByStatus(ProductTypeConstant.PRODUNCT_TYPE_ENABLE);
        return  list;
    }

    @Override
    public void addProductType(String name) throws ProductTypeExitException {
        ProductType productType = productTypeDao.findByName(name);
        if(productType!=null){
            throw new ProductTypeExitException("商品类型已存在");
        }
        productTypeDao.insert(name,ProductTypeConstant.PRODUNCT_TYPE_ENABLE);
    }

    @Override
    public ProductType findById(Integer id) {
        ProductType productType =  productTypeDao.findById(id);
        return productType;
    }

    @Override
    public void modifyName(Integer id, String name) throws ProductTypeExitException {
        ProductType productType = productTypeDao.findByName(name);
        if(productType!=null){
            throw new ProductTypeExitException("商品类型已存在");
        }
        productTypeDao.updateName(id,name);
    }

    @Override
    public void modifyStatus(Integer id) {
        ProductType productType = productTypeDao.findById(id);
        int status = productType.getStatus();
        if(productType.getStatus()==ProductTypeConstant.PRODUNCT_TYPE_ENABLE){
          status = ProductTypeConstant.PRODUNCT_TYPE_DISABLE;
        }else{
            status =ProductTypeConstant.PRODUNCT_TYPE_ENABLE;
        }

        productTypeDao.updateStatus(id,status);
    }

    @Override
    public void deleteName(Integer id) {
        productTypeDao.deleteById(id);
    }


}

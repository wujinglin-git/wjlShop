package com.wjl.wjlShop.service.impl;
import com.wjl.wjlShop.common.util.StringUtils;
import com.wjl.wjlShop.dao.ProductDao;
import com.wjl.wjlShop.dto.ProductDto;
import com.wjl.wjlShop.pojo.Product;
import com.wjl.wjlShop.pojo.ProductType;
import com.wjl.wjlShop.service.ProductService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wugege
 * @Date: 2019/9/9 8:34
 * 一给窝哩 giao giao 呀吼
 *
 */
@Service
@Transactional(propagation =  Propagation.REQUIRED,rollbackFor = Exception.class)

public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public void add(ProductDto productDto) throws FileUploadException {
        /*
        * 1.文件上传
        * 此时将DTO中的输出流传给输出流，即可将要上传的文件的二维码复制到指定目录
        *
        */
        String fileName = StringUtils.renameFileName(productDto.getFileName()) ;

        //之前这里忘了拼接文件名（fileName） 导致拒绝访问 给自己两个大嘴巴子
        String filePath = productDto.getUploadPath()+"/"+fileName;
        try {
            StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(filePath));
        } catch (IOException e) {
            System.out.println("文件上传失败"+e.getMessage());
            throw new FileUploadException("文件上传失败"+e.getMessage());

        }
        //2.保存到数据库,将DTO转化为PO
        Product product = new Product();
        try {
            PropertyUtils.copyProperties(product,productDto);
            product.setImage(filePath);

            ProductType productType = new ProductType();
            productType.setId(productDto.getProductTypeId());
            product.setProductType(productType);

            productDao.insert(product);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean checkName(String name) {

        Product product = productDao.selectByName(name);
        if(product!=null){//商品存在
            return false;
        }else{
            return true;  //商品不存在
        }

    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        products = productDao.selectAll();
        return products;
    }

    @Override
    public void remove(Integer id) {
        productDao.deleteById(id);
    }

    @Override
    public Product findById(Integer id) {
       Product product =  productDao.selectById(id);
        return  product;
    }

    @Override
    public void findImage(String path, OutputStream outputStream) {
        try {
            StreamUtils.copy(new FileInputStream(path),outputStream);
        } catch (IOException e) {


        }
    }

    @Override
    public void modify(ProductDto productDto) throws FileUploadException {
        String fileName = StringUtils.renameFileName(productDto.getFileName()) ;

        //之前这里忘了拼接文件名（fileName） 导致拒绝访问 给自己两个大嘴巴子
        String filePath = productDto.getUploadPath()+"/"+fileName;
        try {
            StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(filePath));
        } catch (IOException e) {
            System.out.println("文件上传失败"+e.getMessage());
            throw new FileUploadException("文件上传失败"+e.getMessage());

        }
        //2.保存到数据库,将DTO转化为PO
        Product product = new Product();
        try {
            PropertyUtils.copyProperties(product,productDto);
            product.setImage(filePath);

            ProductType productType = new ProductType();
            productType.setId(productDto.getProductTypeId());
            product.setProductType(productType);

            productDao.update(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        productDao.deleteById(id);
    }

}

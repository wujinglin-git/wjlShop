package com.wjl.wjlShop.service.impl;

import com.wjl.wjlShop.dao.ProductTypeDao;
import com.wjl.wjlShop.service.ProductTypeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.wjl.wjlShop.*;
import static org.junit.Assert.*;

/**
 * @Author: wugege
 * @Date: 2019/9/2 17:35
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public class ProductTypeServiceImplTest {
    ApplicationContext atx = new ClassPathXmlApplicationContext("spring-dao.xml");
    ProductTypeDao  dao = atx.getBean(ProductTypeDao.class );
    ProductTypeService pr= atx.getBean(ProductTypeService.class );
    @Test
    public void findAll() {
        System.out.println(pr.findAll());
    }
}
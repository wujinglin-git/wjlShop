package com.wjl.wjlShop.service.impl;

import com.wjl.wjlShop.dao.RoleDao;
import com.wjl.wjlShop.pojo.Role;
import com.wjl.wjlShop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wugege
 * @Date: 2019/9/21 15:46
 * 一给窝哩 giao giao 呀吼
 * 4
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        List<Role> list = roleDao.selectAll();
        return list;
    }
}

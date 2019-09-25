package com.wjl.wjlShop.service.impl;

import com.wjl.wjlShop.common.constant.ProductTypeConstant;
import com.wjl.wjlShop.common.constant.SysuserConstant;
import com.wjl.wjlShop.common.excption.ProductTypeExitException;
import com.wjl.wjlShop.common.excption.SysuserException;
import com.wjl.wjlShop.dao.SysuserDao;
import com.wjl.wjlShop.param.SysuserParam;
import com.wjl.wjlShop.pojo.ProductType;
import com.wjl.wjlShop.pojo.Role;
import com.wjl.wjlShop.pojo.Sysuser;
import com.wjl.wjlShop.service.SysuserService;
import com.wjl.wjlShop.vo.SysuserVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @Author: wugege
 * @Date: 2019/9/20 15:10
 * 一给窝哩 giao giao 呀吼
 * 4
 */
@Service
public class SysuserServiceImpl implements SysuserService {

    @Autowired
    SysuserDao sysuserDao;

    @Override
    public List<Sysuser> findAll() {

        List<Sysuser> list = sysuserDao.selectAll();
        return list;
    }

    @Override
    public void add(SysuserVo sysuserVo) throws SysuserException {
        Sysuser sysuser = new Sysuser();
        try {
            PropertyUtils.copyProperties(sysuser,sysuserVo);
            Role role = new Role();
            role.setId(sysuserVo.getRoleId());
            sysuser.setRole(role);
            sysuser.setIsValid(SysuserConstant.SYSUSER_VALID);
            sysuser.setCreateDate(new Date());
            sysuserDao.insert(sysuser);
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

    @Override
    public List<Sysuser> findByParams(SysuserParam sysuserParam) {
        return sysuserDao.selectByParams(sysuserParam);
    }

    @Override
    public void modifyStatusById(int id) {
        int IsValid = sysuserDao.selectStatusById(id);
        if (IsValid == SysuserConstant.SYSUSER_VALID) {
            IsValid = SysuserConstant.SYSUSER_INVALID;
        } else {
            IsValid = SysuserConstant.SYSUSER_VALID;
        }
        sysuserDao.updateStatus(id, IsValid);
    }
}

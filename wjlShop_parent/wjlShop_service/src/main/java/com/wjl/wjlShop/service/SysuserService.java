package com.wjl.wjlShop.service;

import com.wjl.wjlShop.common.excption.SysuserException;
import com.wjl.wjlShop.param.SysuserParam;
import com.wjl.wjlShop.pojo.Sysuser;
import com.wjl.wjlShop.vo.SysuserVo;

import java.util.List;

/**
 * @Author: wugege
 * @Date: 2019/9/20 14:43
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public interface SysuserService {

    public List<Sysuser> findAll();

    public void add(SysuserVo sysuserVo) throws SysuserException;


    List<Sysuser> findByParams(SysuserParam sysuserParam);

    public void modifyStatusById(int id);
}

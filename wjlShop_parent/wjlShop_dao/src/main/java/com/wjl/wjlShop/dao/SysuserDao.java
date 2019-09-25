package com.wjl.wjlShop.dao;

import com.wjl.wjlShop.param.SysuserParam;
import com.wjl.wjlShop.pojo.Sysuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wugege
 * @Date: 2019/9/20 14:53
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public interface SysuserDao {

    public List<Sysuser> selectAll();

    public void insert(Sysuser sysuser);

    public void update(Sysuser sysuser);

    public void updateStatus(@Param("id") int id, @Param("isValid") int isValid);

    public void delete(int id);

    //查询用户名是否重复
    public boolean IsExistLoginName(String name);

    List<Sysuser> selectByParams(SysuserParam sysuserParam);

    public Integer selectStatusById(int id);
}

package com.wjl.wjlShop.vo;

import com.wjl.wjlShop.pojo.Role;

import java.util.Date;

/**
 * @Author: wugege
 * @Date: 2019/9/21 10:12
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public class SysuserVo {
    private Integer id;
    private String name;
    private String loginName;
    private String password;
    private String phone;
    private String email;
    private int roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}

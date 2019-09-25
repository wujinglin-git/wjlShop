package com.wjl.wjlShop.param;

/**
 * Author：汤小洋
 * Date：2018-04-28 11:37
 * Description：<描述>
 */
public class SysuserParam {
    private String name;
    private String loginName;
    private String phone;
    private Integer role;
    private Integer isValid;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}

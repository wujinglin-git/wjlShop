package com.wjl.wjlShop.backend.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjl.wjlShop.common.constant.PaginationConstant;
import com.wjl.wjlShop.common.excption.SysuserException;
import com.wjl.wjlShop.common.util.ResponseResult;
import com.wjl.wjlShop.param.SysuserParam;
import com.wjl.wjlShop.pojo.Role;
import com.wjl.wjlShop.pojo.Sysuser;
import com.wjl.wjlShop.service.RoleService;
import com.wjl.wjlShop.service.SysuserService;
import com.wjl.wjlShop.vo.SysuserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("backend/sysuser")
public class SysUserController {

    @Autowired
    SysuserService sysuserService;

    @Autowired
    RoleService roleService;

    @RequestMapping("/login")
    public String login(){
        //完成登录功能
        return "main";
    }

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,6 );

        List<Sysuser> sysusers = sysuserService.findAll();
        PageInfo<Sysuser> pageInfo = new PageInfo<>(sysusers);
        model.addAttribute("pageInfo",pageInfo);

        return "sysuserManager";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(SysuserVo sysuserVo ,Integer pageNum){
        ResponseResult result = new ResponseResult();
        try {
            sysuserService.add(sysuserVo);
            result.setMessage("添加成功");
        } catch (SysuserException e) {
            result.setMessage("添加失败");
            e.printStackTrace();
        }
        return result;

    }

    @ModelAttribute("roles")
    public List<Role> roleList(){
        List<Role> list = roleService.findAll();
        return list;
    }

    @RequestMapping("/findByParams")
    public String findByParams(SysuserParam sysuserParam, Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.PAGE_NUM;
        }

        PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE);
        List<Sysuser> sysusers=sysuserService.findByParams(sysuserParam);
        PageInfo<Sysuser> pageInfo = new PageInfo<Sysuser>(sysusers);
        model.addAttribute("pageInfo",pageInfo);
        //实现数据回显
        model.addAttribute("sysuserParam",sysuserParam);

        return "sysuserManager";
    }
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(int id){
        sysuserService.modifyStatusById(id);
        return ResponseResult.success();
    }
}

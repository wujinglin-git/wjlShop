package com.wjl.wjlShop.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjl.wjlShop.common.constant.ResponseStatusConstant;
import com.wjl.wjlShop.common.excption.ProductTypeExitException;
import com.wjl.wjlShop.common.util.ResponseResult;
import com.wjl.wjlShop.pojo.ProductType;
import com.wjl.wjlShop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("backend/productType")
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,6 );
        //查询所有种类
        List<ProductType> productTypes = productTypeService.findAll();
        PageInfo<ProductType> pageInfo = new PageInfo<ProductType>(productTypes);


        model.addAttribute("pageInfo",pageInfo);
        return "productTypeManager";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(String name){
        ResponseResult result = new ResponseResult();
        try {
            productTypeService.addProductType(name);
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS);
            result.setMessage("添加成功");
        } catch (ProductTypeExitException e) {
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_FAIL);
            result.setMessage("添加失败");
            e.printStackTrace();
        }
        return result;
    }
    //修改类型
    @RequestMapping("findById")
    @ResponseBody
    public ResponseResult findById(Integer id){
    ProductType productType = productTypeService.findById(id);

        return ResponseResult.success(productType);
    }
    @RequestMapping("/modifyName")
    @ResponseBody
    public  ResponseResult modifyName(Integer id,String name){
        ResponseResult result = new ResponseResult();
        try {
            productTypeService.modifyName(id,name);
            result.setMessage("修改成功");
        } catch (ProductTypeExitException e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /*
    * 修改状态   启用 禁用
    * */
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public  ResponseResult modifyName(Integer id){
        ResponseResult result = new ResponseResult();
        productTypeService.modifyStatus(id);

        return result.success();
    }
    /*
    * 删除类型
    * */
    @RequestMapping("/deleteProductType")
    @ResponseBody
    public ResponseResult deleteName(int id){
        ResponseResult result = new ResponseResult();
        productTypeService.deleteName(id);
        return result.success();
    }
}

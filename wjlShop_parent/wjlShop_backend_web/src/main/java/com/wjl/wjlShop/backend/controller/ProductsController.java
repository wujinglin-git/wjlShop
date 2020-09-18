package com.wjl.wjlShop.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjl.wjlShop.backend.vo.ProductVo;
import com.wjl.wjlShop.common.util.ResponseResult;
import com.wjl.wjlShop.dto.ProductDto;
import com.wjl.wjlShop.pojo.Product;
import com.wjl.wjlShop.pojo.ProductType;
import com.wjl.wjlShop.service.ProductService;
import com.wjl.wjlShop.service.ProductTypeService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wugege
 * @Date: 2019/9/8 15:14
 * 一给窝哩 giao giao 呀吼
 * 4
 */
@Controller
@RequestMapping("backend/product")
public class ProductsController {
    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    ProductService productService;

    @ModelAttribute("productTypes")
    public List<ProductType> productTypeList(){
        List<ProductType> list = productTypeService.findEnable();
        System.out.println("ModelAttribute被调用了");
        return list;
    }
    @ModelAttribute
    public void getProduct(){

    }
    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,6 );

        List<Product> products = productService.findAll();
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo",pageInfo);

        return "productManager";
    }

    @RequestMapping("/add")
    public String add(ProductVo productVo, HttpSession session, Model model,Integer pageNum){
        //获取服务器中需要上传的地址
        String uploadPath = session.getServletContext().getRealPath("WEB-INF/upload");

        /*

        * CommensMutipartFile是web层的组件，不建议添加到service层或者common层，
        * 代码上虽然不会出错，但是会增加耦合度，况且service要想获取文件从上传到服务器的
        * 物理路径还需要调用servletContext，依然调用了web层的组件，所以VO不能直接传给service，
        * 虽然我们不建议直接传输CommonsMultipartFile，但是我们能将其对应的流传进去
        * ，所以我们借助DTO（数据传输对象）来接收action层的流，以实现解耦的目的。
        *
        * */

        //将VO转化为DTO
        try {
            ProductDto productDto = new ProductDto();
            //互考
            PropertyUtils.copyProperties(productDto,productVo);
            productDto.setInputStream(productVo.getFile().getInputStream());
            //返回客户端文件系统中的原始文件名
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            System.out.println(productVo.getFile().getOriginalFilename()+"*****uploadPath****"+uploadPath);

            productDto.setUploadPath(uploadPath);
            productService.add(productDto);
            model.addAttribute("successMessage","添加成功");
            //e.printStackTrace();
          //  model.addAttribute("errorMessage",e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        return "forward:findAll?pageNum="+pageNum;
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String,Object> checkName(String name){
        Map<String,Object> map = new HashMap();
        if(productService.checkName(name)){//不存在，可用
            map.put("valid",true);
        }else{
            map.put("valid",false);
            map.put("message","商品名称已存在");
        }
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id){
        Product product = productService.findById(id);

        return ResponseResult.success(product);
    }

    @RequestMapping("/removeProduct")
    @ResponseBody
    public ResponseResult removeProduct(Integer id){
        ResponseResult result = new ResponseResult();
        productService.remove(id);
        return result.success();
    }

    @RequestMapping("/findImage")
    public void findImage(String path, OutputStream outputStream){
        productService.findImage(path,outputStream);
    }

    @RequestMapping("/modify")
    public String modify(ProductVo productVo, HttpSession session, Model model,Integer pageNum,Product product){
        //获取服务器中需要上传的地址
        String uploadPath = session.getServletContext().getRealPath("WEB-INF/upload");
        try {
            ProductDto productDto = new ProductDto();
            PropertyUtils.copyProperties(productDto,productVo);
            productDto.setInputStream(productVo.getFile().getInputStream());


            //返回客户端文件系统中的原始文件名
            productDto.setFileName(productVo.getFile().getOriginalFilename());

            productDto.setUploadPath(uploadPath);
            productService.modify(productDto);
            model.addAttribute("successMessage","添加成功");

        } catch (Exception e) {
            //e.printStackTrace();
            model.addAttribute("errorMessage",e.getMessage());
        }

        return "forward:findAll?pageNum="+pageNum;
    }
}

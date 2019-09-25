package com.wjl.wjlShop.common.util;

import com.wjl.wjlShop.common.constant.ResponseStatusConstant;
import org.springframework.util.ObjectUtils;

/**
 * @Author: wugege
 * @Date: 2019/9/5 7:44
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public class ResponseResult {
    private int status;

    private String message;

    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ResponseResult success(Object data){
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS,"success",data);
    }

    public static ResponseResult success(String message){
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS,"success",null);
    }

    public static ResponseResult success(){
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS,"success",null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

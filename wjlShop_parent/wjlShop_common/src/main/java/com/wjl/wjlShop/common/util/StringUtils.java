package com.wjl.wjlShop.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: wugege
 * @Date: 2019/9/8 14:52
 * 一给窝哩 giao giao 呀吼
 * 4
 */
public class StringUtils {
    public static String renameFileName(String fileName){
    int dotIndex = fileName.lastIndexOf("."); //返回指定字符在此字符串中最后一次出现处的索引
    String suffix = fileName.substring(dotIndex);  //返回 dotIndex 个 之后的字符，也就是获取xxx.jpg 的jpg后缀
    return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+new Random(100)+suffix;//时间戳做文件名

    }
}

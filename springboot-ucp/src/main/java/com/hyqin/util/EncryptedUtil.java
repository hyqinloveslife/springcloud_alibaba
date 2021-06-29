package com.hyqin.util;

import cn.hutool.crypto.digest.DigestUtil;

import java.util.Random;

/**
 * @description 加密算法工具类
 * @author: huangyeqin
 * @create : 2021/6/24  14:35
 */
public class EncryptedUtil {
    // 生成盐的字符串
    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    // 生成盐的长度
    private static int capacity = 16;

    private static String defaultPasswordSuffix = "@abc";

    /**
     * @Desc : 生成盐的方法
     * @Author : huangyeqin
     * @Date : 2021/6/24 14:37
     * @Param :
     * @Result : java.lang.String
     **/
    public static String getSalt() {
        return getRandomStr(capacity);
    }

    /**
     * @Desc : 加盐算法
     * @Author : huangyeqin
     * @Date : 2021/6/24 14:44
     * @Param : inStr
     * @Result : java.lang.String
     **/
    public static String MD5WithSalt(String inStr) {
        return generate(inStr, getSalt());
    }

    /**
     * @Desc : 直接md5加密,不要盐
     * @Author : huangyeqin
     * @Date : 2021/6/24 14:41
     * @Param : inStr
     * @Result : java.lang.String
     **/
    public static String MD5WithoutSalt(String inStr) {
        return generate(inStr, null);
    }

    /**
     * @Desc : 进行md5加密
     * @Author : huangyeqin
     * @Date : 2021/6/24 14:48
     * @Param : password
     * @param: salt
     * @Result : java.lang.String
     **/
    private static String generate(String password, String salt) {
        return DigestUtil.md5Hex(password + (salt == null ? "" : salt));
    }

    /**
     * @Desc : 生成默认的密码
     * @Author : huangyeqin
     * @Date : 2021/6/24 14:47
     * @Param :
     * @Result : java.lang.String
     **/
    public static String generateDefaultPassword() {
        return getRandomStr(6) + defaultPasswordSuffix;
    }

    /**
     * @Desc : 生成指定位数的随机数
     * @Author : huangyeqin
     * @Date : 2021/6/24 14:48
     * @Param : capacity
     * @Result : java.lang.String
     **/
    public static String getRandomStr(int capacity) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(capacity);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(hex[random.nextInt(capacity)]);
        }
        return sb.toString();
    }

}

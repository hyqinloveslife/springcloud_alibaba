package com.hyqin.domain.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.ibatis.annotations.ConstructorArgs;

/**
 * @description 定义前端访问错误码的枚举
 * @author: huangyeqin
 * @create : 2021/6/23  8:32
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    RESPONSE_CODE_PARAM_ERR_400(400,"服务器不理解请求的语法"),
    RESPONSE_CODE_NOT_FOUND_404(404,"访问的资源不存在"),
    RESPONSE_CODE_METHOD_NOT_SUPPORT_405(405,"禁用请求中指定的方法"),
    RESPONSE_CODE_TYPE_NOT_ACCEPTABLE_406(406,"无法使用请求的内容特性响应请求的网页"),
    RESPONSE_CODE_FAIL(-1,"操作失败");

    /**
     * 返回码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

}

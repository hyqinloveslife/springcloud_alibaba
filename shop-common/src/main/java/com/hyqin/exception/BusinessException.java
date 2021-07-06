package com.hyqin.exception;

import com.hyqin.domain.config.ResponseEnum;

/**
 * @description 自定义异常
 * @author: huangyeqin
 * @create : 2021/6/30  9:22
 */
public class BusinessException extends RuntimeException {
    private ResponseEnum responseEnum;

    public BusinessException(ResponseEnum responseEnum) {
        super();
        this.responseEnum = responseEnum;
    }

    public int getErrorCode() {
        return responseEnum.getCode();
    }

    public String getErrorMsg() {
        return responseEnum.getMsg();
    }

}

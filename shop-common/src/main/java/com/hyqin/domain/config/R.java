package com.hyqin.domain.config;

import java.io.Serializable;
import lombok.Data;

/**
 * @description 包装返回类
 * @author: huangyeqin
 * @create : 2021/1/9  15:59
 */
@Data
public class R<T> implements Serializable {

  public static int SUCCESS = 200;
  public static int FAIL = -1;
  public static String MSG_SUCCESS = "成功";
  public static String MSG_WARNING = "成功但有告警";
  public static String MSG_FAIL = "失败";

  /**
   * 返回码
   */
  private int code;

  /**
   * 返回值
   */
  private String message;

  /**
   * 返回值类型
   */
  private String type;

  /**
   * 返回数据
   */
  private T data;

  public R() {
  }

  public R(int code, String message, String type, T data) {
    this.code = code;
    this.message = message;
    this.type = type;
    this.data = data;
  }

  /**
   * 返回成功
   *
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/9 16:36
   * @Param : null
   * @Result :
   */
  public static <T> R<T> success(T data) {
    return new R(SUCCESS, ResponseType.TYPE_SUCCESS.getType(), MSG_SUCCESS, data);
  }

  public static <T> R<T> success(String message, T data) {
    message = message != null && message.length() > 0 ? message : MSG_SUCCESS;
    return new R(SUCCESS, ResponseType.TYPE_SUCCESS.getType(), message, data);
  }

  public static enum ResponseType {
    TYPE_SUCCESS("success"),
    TYPE_INFO("info"),
    TYPE_WARNING("warning"),
    TYPE_ERROR("error");

    private String type;

    private ResponseType(String type) {
      this.type = type;
    }

    public String getType() {
      return this.type;
    }
  }

}

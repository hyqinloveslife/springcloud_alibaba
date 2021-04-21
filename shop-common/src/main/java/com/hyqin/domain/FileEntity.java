package com.hyqin.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * @description fastdfs的实体类
 * @author: huangyeqin
 * @create : 2021/1/6  11:50
 */
@Data
public class FileEntity implements Serializable {
  /** 保存从文件服务器返回的id，需要通过该id去文件服务器中下载文件 */
  private String fileId;

  /** 保存的文件名 */
  private String fileName;

  /** 文件类型，就是文件扩展名 */
  private String fileType;

  /** 文件大小 ，单位是字节b */
  private Long fileSize;


}

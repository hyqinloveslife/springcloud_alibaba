package com.hyqin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * @description 附件的do类
 * @author: huangyeqin
 * @create : 2021/1/6  15:41
 */
@Data
@TableName(value = "fdfs_file")
@ApiModel(value = "附件的DO类，与数据库中表字段对应")
public class FileDO {
  @ApiModelProperty(value = "主键id")
  @TableId(value = "id",type = IdType.AUTO)
  private String id;

  /** 保存从文件服务器返回的id，需要通过该id去文件服务器中下载文件 */
  @ApiModelProperty(value = "保存从文件服务器返回的id")
  @TableId(value = "file_id")
  private String fileId;

  /** 保存的文件名 */
  @ApiModelProperty(value = "保存的文件名")
  @TableId(value = "file_name")
  private String fileName;

  /** 文件类型，就是文件扩展名 */
  @ApiModelProperty(value = "文件类型")
  @TableId(value = "file_type")
  private String fileType;

  /** 文件大小 ，单位是字节Byte */
  @ApiModelProperty(value = "文件大小")
  @TableId(value = "file_size")
  private Long fileSize;

  @ApiModelProperty(value = "附件上传时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  @TableId(value = "crte_time")
  private Date crteTime;

  @ApiModelProperty(value = "附件上传人")
  @TableId(value = "crte_name")
  private String crteName;
}

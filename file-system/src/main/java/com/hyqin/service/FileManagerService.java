package com.hyqin.service;

import com.hyqin.domain.FileEntity;
import com.hyqin.entity.FileDO;
import java.io.File;
import java.util.List;

/**
 * @description 附件管理service
 * @author: huangyeqin
 * @create : 2021/1/6  11:57
 */
public interface FileManagerService {
  /**
   *
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/6 11:58
   * @Param : file  存到服务器的文件名<需要先缓存到本地>
              fileName  文件名
              fileType  文件类型
   * @Result : com.hyqin.domain.FileEntity
  */
  FileEntity fileUpload(File file,String fileName,String fileType);

  /**
   * 查询附件列表
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/6 16:01
   * @Param :
   * @Result : java.util.List<com.hyqin.entity.FileDO>
  */
  List<FileDO> list();

  /**
   * 根据表的id来获取文件在服务器的id
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/6 17:21
   * @Param : id
   * @Result : com.hyqin.domain.FileEntity
  */
  FileEntity info(String id);
}

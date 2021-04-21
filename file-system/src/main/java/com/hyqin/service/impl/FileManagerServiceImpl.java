package com.hyqin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyqin.config.MyStorageClient;
import com.hyqin.dao.FileManagerDao;
import com.hyqin.domain.FileEntity;
import com.hyqin.entity.FileDO;
import com.hyqin.service.FileManagerService;
import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.NameValuePair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description 附件管理实现类
 * @author: huangyeqin
 * @create : 2021/1/6  11:59
 */
@Slf4j
@Service
public class FileManagerServiceImpl implements FileManagerService {

  @Autowired
  private MyStorageClient storageClient;

  @Resource
  private FileManagerDao fileManagerDao;

  @Value("${fdfs.url}")
  private String webUrl;

  @Value("${fdfs.port}")
  private String webFilePort;

  @Override
  public FileEntity fileUpload(File file, String fileName, String fileType) {
    // 封装需要返回的信息
    FileEntity fileEntity = new FileEntity();
    fileEntity.setFileName(fileName);
    fileEntity.setFileType(fileType);
    fileEntity.setFileSize(file.length());

    // 设置文件的原信息
    NameValuePair[] meteList = new NameValuePair[2];
    meteList[0] = new NameValuePair("filename", fileName);
    meteList[1] = new NameValuePair("filepath", file.getAbsolutePath());
    try {
      String fileId = storageClient.getClient()
          .upload_file1(file.getAbsolutePath(), fileType, meteList);
      fileEntity.setFileId(fileId);
    } catch (Exception e) {
      log.error("上传文件失败");
    }

    // 将文件业务数据保存到数据库中
    FileDO entity = new FileDO();
    BeanUtils.copyProperties(fileEntity,entity);
    fileManagerDao.insert(entity);

    return fileEntity;
  }

  @Override
  public List<FileDO> list() {
    QueryWrapper<FileDO> queryWrapper = new QueryWrapper<>();
    List<FileDO> fileDOS = fileManagerDao.selectList(queryWrapper);
    return fileDOS;
  }

  @Override
  public FileEntity info(String id) {
    FileDO fileDO = fileManagerDao.selectById(id);
    FileEntity target = new FileEntity();
    BeanUtils.copyProperties(fileDO, target);
    target.setFileId(webUrl+":"+webFilePort+"/"+target.getFileId());
    return target;
  }
}

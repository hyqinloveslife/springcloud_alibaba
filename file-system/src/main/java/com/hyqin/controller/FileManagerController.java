package com.hyqin.controller;

import com.hyqin.domain.config.R;
import com.hyqin.config.annotation.Api_Business;
import com.hyqin.domain.FileEntity;
import com.hyqin.entity.FileDO;
import com.hyqin.service.FileManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description 附件管理的接口
 * @author: huangyeqin
 * @create : 2021/1/6  11:55
 */
@Slf4j
@Api(value = "附件管理", tags = "附件管理-api")
@Api_Business
@RequestMapping("/file-manager")
@RestController
public class FileManagerController {

  @Value("${fdfs.local-temp-path}")
  private String localTempPath;

  @Autowired
  private FileManagerService fileManagerService;

  /**
   * 附件上传接口
   *
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/6 11:57
   * @Param : file
   * @Result : com.hyqin.domain.FileEntity
   */
  // @RequestParam("file") MultipartFile file
  @ApiOperation(value = "文件上传")
  @ResponseBody
  @PostMapping("/upload")
  public FileEntity fileUpload(@RequestParam("upload") MultipartFile upload) {
//    File file = new File("D:\\Docker\\1.jpg");
    File file = null;
    try {
      file = fileTransfer(upload);
    } catch (IOException e) {
      log.error("在进行文件复制的时候报错");
      e.printStackTrace();
    }
    FileEntity fileEntity = fileManagerService
        .fileUpload(file, file.getName(), file.getName().split("\\.")[1]);
    return fileEntity;
  }

  /**
   * 将上传的临时文件保存到本地
   *
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/6 15:05
   * @Param : null
   * @Result :
   */
  private File fileTransfer(MultipartFile file) throws IOException {
    // 在本地找一个文件夹, 生成一个空文件
    String[] split = file.getOriginalFilename().split("\\.");
    String fileName = split[0];
    String fileType = split[1];
//    String fileExtentionType = file.getOriginalFilename()
//        .substring(file.getOriginalFilename().lastIndexOf("."));
    File newFile = new File(localTempPath + fileName + "." + fileType);
    // 把传入的文件复制到空文件里面
    file.transferTo(newFile);
    return newFile;
  }

  @ApiOperation(value = "数据库中文件列表查询 - 无包装类")
  @GetMapping("/list-local")
  @ResponseBody
  public List<FileDO> list() {
    return fileManagerService.list();
  }

  @ApiOperation(value = "数据库中文件列表查询")
  @GetMapping("/list")
  @ResponseBody
  public R<List<FileDO>> lists() {
    return R.success(fileManagerService.list());
  }

  @ApiOperation(value = "下载")
  @PostMapping("/download")
  @ResponseBody
  public void download() {

  }

  /**
   * 预览
   *
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/6 17:18
   * @Param : id
   * @Result : java.lang.String
   */
  @ApiOperation(value = "预览-获取文件信息")
  @PostMapping("/view")
  @ResponseBody
  public R<FileEntity> view(@RequestBody Map param) {
    String id = param.get("id").toString();
    FileEntity fileEntity = fileManagerService.info(id);
    return R.success(fileEntity);
  }
}

package com.hyqin.outlets;

import java.io.File;
import java.io.IOException;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdfs.pool.Connection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description 测试文件系统
 * @author: huangyeqin
 * @create : 2021/1/6  10:47
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FileSystemTest {

//  private StorageClient storageClient;
//
//  private TrackerServer trackerServer;
//
//  private StorageClient1 client1;
//
//  @Before
//  public void init() throws IOException, MyException {
//    ClientGlobal.initByProperties("config/fastdfs-config.properties");
//    TrackerClient tracker = new TrackerClient();
//
//    trackerServer = tracker.getTrackerServer();
//
//    StorageServer storageServer = null;
//
//    storageClient = new StorageClient(trackerServer,storageServer);
//
//    client1 = new StorageClient1(trackerServer,storageServer);
//  }
//
//
//  @Test
//  public void test1() throws IOException, MyException {
//    // 组装文件的元信息
//    NameValuePair[] mateList = new NameValuePair[1];
//    String fileName = "武当王也.jpg";
//    mateList[0] = new NameValuePair("filename",fileName);
//
//    String filePath = "D:\\Docker\\1.jpg" ;
//    File file = new File("D:\\Docker\\1.jpg");
////    String[] resultArray = storageClient.upload_file(filePath, "jpg", mateList);
//
//    String fileId = client1.upload_file1(filePath, "jpg", mateList);
//    System.out.println("查看返回结果："+fileId);
//  }

}

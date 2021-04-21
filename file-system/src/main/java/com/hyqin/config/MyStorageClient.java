package com.hyqin.config;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/1/6  12:05
 */
@Component
public class MyStorageClient extends StorageClient1 {
  private static final String CONF_FILENAME = "config/fastdfs-config.properties";

  public StorageClient1 getClient() {
    try {
      ClientGlobal.initByProperties(CONF_FILENAME);
      System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
      System.out.println("charset=" + ClientGlobal.g_charset);

      TrackerClient tracker = new TrackerClient();
      TrackerServer trackerServer = tracker.getTrackerServer();
      StorageServer storageServer = null;
      StorageClient1 client = new StorageClient1(trackerServer, storageServer);
      return client;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

}

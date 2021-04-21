package com.hyqin.test;

import com.hyqin.utils.IdWorker;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/1/14  15:48
 */
public class TestMain {

  public static void main(String[] args) {
    IdWorker idWorker = new IdWorker();
    for (int i = 0; i < 10; i++) {
      long id = idWorker.nextId();
      System.out.println(id + " 位数："+ String.valueOf(id).length());
    }
  }
}

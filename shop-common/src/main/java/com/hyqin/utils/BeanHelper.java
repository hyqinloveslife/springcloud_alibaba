package com.hyqin.utils;

import cn.hutool.core.bean.BeanUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/2/2  14:32
 */
public class BeanHelper {
  public static <T> List<T> convertToBeanList(List<?> sourceList, Class<T> clazz) throws Exception {
    ArrayList resultList = null;

    try {
      if (sourceList != null && sourceList.size() > 0) {
        resultList = new ArrayList();
        Iterator iterator = sourceList.iterator();

        while(iterator.hasNext()) {
          T t = clazz.newInstance();
          Object sourceObject = iterator.next();
          BeanUtil.copyProperties(sourceObject, t, new String[0]);
          resultList.add(t);
        }
      }

      return resultList;
    } catch (Exception var6) {
      var6.printStackTrace();
      throw new Exception("转换List<bean>对象出错：" + var6.getMessage());
    }
  }
}

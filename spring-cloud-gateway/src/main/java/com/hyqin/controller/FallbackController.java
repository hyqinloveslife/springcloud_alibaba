package com.hyqin.controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description 降级处理
 * @author: huangyeqin
 * @create : 2021/2/3  14:07
 */
@RestController
public class FallbackController {

  private static final Logger logger = LoggerFactory.getLogger(FallbackController.class);

  @GetMapping("/fallback")
  public Map fallback(){
    logger.info("========================是否触发熔断机制========================");
    Map map = new HashMap();
    map.put("message","服务暂时不可用");
    map.put("code","411");
    return map;
  }
}

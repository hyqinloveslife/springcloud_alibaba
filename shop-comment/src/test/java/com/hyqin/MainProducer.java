package com.hyqin;

import com.alibaba.fastjson.JSON;
import com.hyqin.domain.User;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @description 测试生产者
 * @author: huangyeqin
 * @create : 2021/2/7  14:40
 */

public class MainProducer {

  public static void main(String[] args) throws MQClientException {
    DefaultMQProducer producer = new DefaultMQProducer("test-group");

    producer.setNamesrvAddr("127.0.0.1:9876");

    producer.setInstanceName("rmq-instance");

    producer.start();

    try {

      for (int i = 0; i < 20; i++) {
        User user = new User();

        user.setUsername("abc"+i);
        user.setPassword("123456");
        user.setTelephone("15611112222");
        // 以字节的方式发送？ 那么底层还是用socket来传数据吗
        Message message = new Message("log-topic","user-tag", JSON.toJSONString(user).getBytes());

        System.out.println("生产者发送消息:"+JSON.toJSONString(user));

        producer.send(message);

      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    producer.shutdown();
  }
}

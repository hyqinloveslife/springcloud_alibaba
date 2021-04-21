package com.hyqin;

import com.hyqin.domain.User;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @description 消息的消费者
 * @author: huangyeqin
 * @create : 2021/2/7  16:31
 */
public class MainConsumer {

  public static void main(String[] args) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-group");

    consumer.setNamesrvAddr("127.0.0.1:9876");
    consumer.setInstanceName("rmq-instance");
    consumer.subscribe("log-topic", "user-tag");

    consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
      for (MessageExt msg : msgs) {
        System.out.println("消费者开始消费消息：" + new String(msg.getBody()));
      }
      return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    });
    consumer.start();

  }

}

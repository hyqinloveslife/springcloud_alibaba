package com.hyqin;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @description 异步的消息发送
 * @author: huangyeqin
 * @create : 2021/2/8  20:53
 */
public class AsynProducer {

  public static void main(String[] args)
      throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
    // 实例化消息生产者 producer
    DefaultMQProducer producer = new DefaultMQProducer(ConstantConfig.PRODUCER_GROUP);

    // 设置nameserver 的服务地址
    producer.setNamesrvAddr(ConstantConfig.NAMESERVER);

    // 启动线程
    producer.start();

    // 应该是设置重试时间
    producer.setRetryTimesWhenSendAsyncFailed(0);

    // 设置消息总量
    int messageCount = 100;

    // 根据消息数量实例化倒计时计算器
    final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);

    for (int i = 0; i < messageCount; i++) {
      // 这里为什么要定义成final类型；虽然不加这个final也是一样的效果
      final int index = 1;

      // 创建消息，并指定topic，tag和消息体
      Message message = new Message(ConstantConfig.TOPIC_NAME, ConstantConfig.TAG, "orderId188",
          "hello world".getBytes(
              RemotingHelper.DEFAULT_CHARSET));

      producer.send(message, new SendCallback() {
        @Override
        public void onSuccess(SendResult sendResult) {
          System.out.println("消息发送成功");
          System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
        }

        @Override
        public void onException(Throwable e) {
          System.out.printf("%-10d Exception %s %n", index, e);
          e.printStackTrace();
        }
      });
    }

    // 等待5秒
    countDownLatch.await(5, TimeUnit.SECONDS);
    producer.shutdown();
  }

}

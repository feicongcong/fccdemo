package com.example.rocketMq.simple;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.72.133:9876");
        //重试次数，默认2次
        producer.setRetryTimesWhenSendFailed(2);
        producer.start();

        for (int i = 0; i <10 ; i++) {
            Message msg = new Message("TopicTest",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            //同步传递消息，消息会发给集群中的一个Broker节点。
            SendResult result = producer.send(msg);
            System.out.println(result);
            //单向发送，不用服务器响应
            producer.sendOneway(msg);
        }
//        producer.shutdown();
    }
}

package com.example.kafka.kafkademo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class TransProducer {

    /*
    Kafka的事务不同于Rocketmq，Rocketmq是保障本地事务(比如数据库)与mq消息发送的事务一致性，
    Kafka的事务主要是保障一次发送多条消息的事务一致性(要么同时成功要么同时失败)，一般在kafka的流式计算场景用得多一点，
    比如，kafka需要对一个topic里的消息做不同的流式计算处理，处理完分别发到不同的topic里，这些topic分别被不同的下游系统消费(比如hbase，redis，es等)，
    这种我们肯定希望系统发送到多个topic的数据保持事务一致性
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("transactional.id", "my-transactional-id");
        Producer<String, String> producer = new KafkaProducer<>(props, new StringSerializer(), new StringSerializer());
        //初始化事务
        producer.initTransactions();

        try {
            //开启事务
            producer.beginTransaction();
            for (int i = 0; i < 100; i++){
                //发到不同的主题的不同分区
                producer.send(new ProducerRecord<>("hdfs-topic", Integer.toString(i), Integer.toString(i)));
                producer.send(new ProducerRecord<>("es-topic", Integer.toString(i), Integer.toString(i)));
                producer.send(new ProducerRecord<>("redis-topic", Integer.toString(i), Integer.toString(i)));
            }
            //提交事务
            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            // We can't recover from these exceptions, so our only option is to close the producer and exit.
            producer.close();
        } catch (KafkaException e) {
            // For all other exceptions, just abort the transaction and try again.
            //回滚事务
            producer.abortTransaction();
        }
        producer.close();
    }
}

package com.hnu.learn.kafka.chapter1;

import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Kafka 消息生产者
 */
public class ProducerFastStart {
    //
    // Kafka集群地址
    private static final String brokerList = "hadoop-101:9092,hadoop-102:9092,hadoop-103:9092";
    // 主题名称-之前已经创建
    private static final String topic = "Hulala-par";

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 设置key序列化器
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //另外一种写法
        //properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 设置重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);
        // 设置值序列化器
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 设置集群地址
        properties.put("bootstrap.servers", brokerList);
        // KafkaProducer 线程安全
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "Kafka-demo-001", "hello, Kafka!");
        try {
            //同步发送
            KafkaSynchronousSend( producer,record );
            //异步发送，就是不阻塞当前业务，重新起一个线程来做发送任务
            KafkaAsynchronousSend( producer,record );
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }

    public static void KafkaSynchronousSend(KafkaProducer<String, String> producer,ProducerRecord<String, String> record ) throws ExecutionException, InterruptedException {

        Future<RecordMetadata> send = producer.send(record);
        RecordMetadata recordMetadata = send.get();
        System.out.println("partition:" + recordMetadata.partition()
                + ";topic:" + recordMetadata.topic()
                + ";offset:" + recordMetadata.offset());
    }

//    public static void fun()
//    {//https://blog.csdn.net/yfm081616/article/details/115609669
//        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, o);
//        future.addCallback(result -> logger.info("生产者成功发送消息到topic:{} partition:{}的消息", result.getRecordMetadata().topic(), result.getRecordMetadata().partition()),
//                ex -> logger.error("生产者发送消失败，原因：{}", ex.getMessage()));
//
//    }



    public static void KafkaAsynchronousSend(KafkaProducer<String, String> producer,ProducerRecord<String, String> record ){

        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.println(    "partition:" + metadata.partition()
                            + ";topic:" + metadata.topic()
                            + ";offset:" + metadata.offset());
                }
            }
        });


    }
}

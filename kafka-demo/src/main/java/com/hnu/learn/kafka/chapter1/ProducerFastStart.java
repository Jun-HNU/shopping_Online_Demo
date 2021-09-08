package com.hnu.learn.kafka.chapter1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Kafka 消息生产者
 */
public class ProducerFastStart {
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
            Future<RecordMetadata> send = producer.send(record);
            RecordMetadata recordMetadata = send.get();
            System.out.println("partition:" + recordMetadata.partition()
                    + ";topic:" + recordMetadata.topic()
                    + ";offset:" + recordMetadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}

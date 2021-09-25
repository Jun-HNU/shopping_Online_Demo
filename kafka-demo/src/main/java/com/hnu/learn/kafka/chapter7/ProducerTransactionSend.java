package com.hnu.learn.kafka.chapter7;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Kafka Producer事务的使用
 */
public class ProducerTransactionSend {
    public static final String topic = "heima";
    public static final String brokerList = "localhost:9092";
    public static final String transactionId = "transactionId";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());


        /*只能保证 Producer 在单个会话内不丟不重，如果 Producer 出现意外挂掉再重启是无法保证的
（幂等性情况下，是无法获取之前的状态信息，因此是无法做到跨会话级别的不丢不重）;
        幂等性不能跨多个 Topic-Partition，只能保证单个 partition 内的幂等性，当涉及多个 TopicPartition 时，这中间的状态并没有同步。*/
        //properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
       // properties.put("acks", "all"); // 当 enable.idempotence 为 true，这里默认为 all

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);


        /*
        幂等性并不能跨多个分区运作，而事务可以弥补这个缺憾，事务可以保证对多个分区写入操作的原子
性。操作的原子性是指多个操作要么全部成功，要么全部失败，不存在部分成功部分失败的可能。
为了实现事务，应用程序必须提供唯一的transactionalId，这个参数通过客户端程序来进行设定。
         */
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionId);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        producer.initTransactions();
        producer.beginTransaction();

        try {
            //处理业务逻辑并创建ProducerRecord
            ProducerRecord<String, String> record1 = new ProducerRecord<>(topic, "msg1");
            producer.send(record1);

            //模拟事务回滚案例
            System.out.println(1/0);

            ProducerRecord<String, String> record2 = new ProducerRecord<>(topic, "msg2");
            producer.send(record2);
            ProducerRecord<String, String> record3 = new ProducerRecord<>(topic, "msg3");
            producer.send(record3);
            //处理一些其它逻辑
            producer.commitTransaction();
        } catch (ProducerFencedException e) {
            producer.abortTransaction();
        }
        producer.close();
    }

}

package com.hnu.learn.kafka.chapter3;

import com.hnu.learn.kafka.ConsumerClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 异步提交
 */
@Slf4j
public class OffsetCommitAsyncCallback extends ConsumerClientConfig {

    private static AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        Properties props = initConfig();
        //将自动提交改为false,后面每次消费后，手动调用consumer的commitAsync()或commitSync();方法进行提交。
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

        try {
            while (running.get()) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    //do some logical processing.
                }
                // 异步回调
                consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets,
                                           Exception exception) {
                        if (exception == null) {
                            System.out.println(offsets);
                        } else {
                            log.error("fail to commit offsets {}", offsets, exception);
                        }
                    }
                });
            }
        } finally {
            consumer.close();
        }

        try {
            while (running.get()) {
                consumer.commitAsync();
            }
        } finally {
            try {
//在业务中写异步提交，在最后写同步提交。提高offset提交的成功率。
                //无论是否发生异常都会执行这里同步提交，提高offset提交的成功率。
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }
}

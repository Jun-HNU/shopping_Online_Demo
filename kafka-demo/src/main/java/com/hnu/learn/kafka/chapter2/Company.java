package com.hnu.learn.kafka.chapter2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.internals.BufferPool;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    //BufferPool
    private String name;
    private String address;
//    private String telphone;
}

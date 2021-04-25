package com.hnu.shopping.conf;

import com.hnu.shopping.util.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
    //读取配置文件中的redis的ip地址
    @Value("${spring.redis.host:disabled}")
    private String host;
    @Value("${spring.redis.port:0}")
    private int port ;
    @Value("${spring.redis.database:0}")
    private int database;
    @Bean
    public RedisUtils getRedisUtils(){
        if(host.equals("disabled")){
            return null;
        }
        RedisUtils RedisUtils=new RedisUtils();
        RedisUtils.initPool(host,port,database);
        return RedisUtils;
    }
}

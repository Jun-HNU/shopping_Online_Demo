package com.hnu.shopping;

import com.hnu.shopping.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.hnu.shopping.manage.mapper")
public class GmallManageServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(com.hnu.shopping.GmallManageServiceApplication.class, args);


	}

}

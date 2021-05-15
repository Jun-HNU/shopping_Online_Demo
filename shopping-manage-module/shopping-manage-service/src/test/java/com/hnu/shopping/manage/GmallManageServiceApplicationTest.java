package com.hnu.shopping.manage;

import com.hnu.shopping.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageServiceApplicationTest {

	@Autowired
	RedisUtils RedisUtils;

	@Test
	public void contextLoads() {
		System.out.println(RedisUtils);

			Jedis jedis = RedisUtils.getJedis();
		System.out.println(jedis);
	}

}

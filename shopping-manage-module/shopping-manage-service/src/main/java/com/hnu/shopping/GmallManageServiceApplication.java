package com.hnu.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.hnu.shopping.manage.mapper")
public class GmallManageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.hnu.shopping.GmallManageServiceApplication.class, args);
	}

}

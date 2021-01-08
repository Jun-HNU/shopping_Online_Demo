package com.hnu.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GmallItemWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.hnu.shopping.GmallItemWebApplication.class, args);
	}

}

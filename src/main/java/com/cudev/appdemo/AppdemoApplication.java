package com.cudev.appdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppdemoApplication.class, args);
	}

}

package com.nwl.lanya;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.nwl.lanya.dao")
@SpringBootApplication
@ComponentScan(basePackages= {"com.nwl.lanya"})
public class LanyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanyaApplication.class, args);
	}
}

package com.dtb.store_of_value_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class StoreOfValueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreOfValueServiceApplication.class, args);
	}

}

package com.udt.udt_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UdtEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdtEcommerceApplication.class, args);
	}

}

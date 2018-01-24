package com.quentin.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class QuetinbootApplication {
	public static void main(String[] args) {
		log.info("I am debug log.");
		SpringApplication.run(QuetinbootApplication.class, args);
		log.error("I am debug log.");
		log.warn("I am debug log.");
	}
}

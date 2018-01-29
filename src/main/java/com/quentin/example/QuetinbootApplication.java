package com.quentin.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement//如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@SpringBootApplication
@Slf4j
public class QuetinbootApplication {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();//获取当前时间
		SpringApplication.run(QuetinbootApplication.class, args);
		long endTime = System.currentTimeMillis();
		log.info("程序启动时间："+(endTime-startTime)+"ms");
	}
}

package com.quentin.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableTransactionManagement//如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@SpringBootApplication
@Slf4j
public class QuetinbootApplication {
	public static void main(String[] args) throws UnknownHostException {
		long startTime = System.currentTimeMillis();//获取当前时间
		//SpringApplication.run(QuetinbootApplication.class, args);

		SpringApplication app = new SpringApplication(QuetinbootApplication.class);
		Environment env = app.run(args).getEnvironment();
		log.info("Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\thttp://127.0.0.1:{}\n\t" +
						"External: \thttp://{}:{}\n" +
						"----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));

		long endTime = System.currentTimeMillis();
		log.info("程序启动时间："+(endTime-startTime)+"ms");
	}
}

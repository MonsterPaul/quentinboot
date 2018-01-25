package com.quentin.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuetinbootApplicationTests {
	@Value("${myapp.bignumber}")
	private long randomLongNumber;
	@Value("${myapp.number}")
	private int randomIntNumber;
	@Value("${myapp.secret}")
	private String randomStr;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRandom() {
		log.info("随机long类型："+randomLongNumber);
		log.info("随机int类型："+randomIntNumber);
		log.info("随机字符串类型："+randomStr);
	}

}

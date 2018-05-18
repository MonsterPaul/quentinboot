package com.quentin.example;

import com.quentin.example.domain.UserVO;
import com.quentin.example.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 17:20 2018/2/1
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestUser{

    @Autowired
    private IUserService userService;
    @Test
    public void testQueryUserByName() {
        UserVO userVO = (UserVO) userService.queryUserByName("admin");
        System.out.println(userVO.getUserName());
    }
}

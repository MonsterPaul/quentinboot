package com.quentin.example.web.controller;

import com.quentin.example.domain.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Controller 父类
 *
 * @Author Created by guoqun.yang
 * @Date Created in 16:01 2018/3/6
 * @Version 1.0
 */
@Controller
@Scope("prototype")
@Slf4j
public class BasicContrller {

    /**
     * 登录用户名
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/3/6 16:03
     * @version 1.0
     */
    public String getCurrentLoginName() {
        Subject currentUser = SecurityUtils.getSubject();
        UserVO user = currentUser.getPrincipals().oneByType(UserVO.class);
        return user.getUsername();
    }

    /**
     * 登陆用户id
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/3/6 16:03
     * @version 1.0
     */
    public Long getCurrentLoginId() {
        Subject currentUser = SecurityUtils.getSubject();
        UserVO user = currentUser.getPrincipals().oneByType(UserVO.class);
        return user.getUserId();
    }

    /**
     * 登录用户对象
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/3/6 16:03
     * @version 1.0
     */
    public UserVO getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        UserVO user = currentUser.getPrincipals().oneByType(UserVO.class);
        return user;
    }
}

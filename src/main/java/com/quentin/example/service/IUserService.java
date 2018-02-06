package com.quentin.example.service;

import com.quentin.example.config.dynamicsdb.DataSource;
import com.quentin.example.config.dynamicsdb.DataSourceEnum;
import com.quentin.example.domain.UserVO;

import java.util.List;

/**
 * 用户操作服务层
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 16:13 2018/2/1
 * @Version 1.0
 */
@DataSource(name = DataSourceEnum.predb)
public interface IUserService {

    /**
     * 根据用户名获取用户
     * @Author: guoqun.yang
     * @Date:   2018/2/1 17:04
     * @param
     * @version 1.0
     */
    List<UserVO> queryUserByName(String username);
}

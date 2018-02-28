package com.quentin.example.service.impl;

import com.quentin.example.domain.UserVO;
import com.quentin.example.domain.mapper.UserVOMapper;
import com.quentin.example.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 17:05 2018/2/1
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserVOMapper userVOMapper;

    @Override
    public List<UserVO> queryUserByName(String username) {
        return userVOMapper.queryByUserName(username);
    }
}

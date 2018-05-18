package com.quentin.example.domain.mapper;

import com.quentin.example.domain.UserVO;
import com.quentin.example.config.basemapper.QuentinMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author Created by guoqun.yang
 * @version 1.0
 * @date Created in 15:47 2018/2/1
 */
public interface UserMapper extends QuentinMapper<UserVO> {
    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return com.quentin.example.domain.UserVO
     * @author guoqun.yang
     * @date 2018/4/25 21:12
     * @version 1.0
     */
    List<UserVO> queryByUserName(@Param("userName") String userName);
}
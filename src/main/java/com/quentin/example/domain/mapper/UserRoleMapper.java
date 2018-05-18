package com.quentin.example.domain.mapper;

import com.quentin.example.config.basemapper.QuentinMapper;
import com.quentin.example.domain.UserRoleVO;

import java.util.List;

/**
 * 用户角色Mapper
 *
 * @Author Created by guoqun.yang
 * @Date Created in 15:47 2018/2/1
 * @Version 1.0
 */
public interface UserRoleMapper extends QuentinMapper<UserRoleVO> {

    List<Integer> findUserIdByRoleId(Integer roleId);
}
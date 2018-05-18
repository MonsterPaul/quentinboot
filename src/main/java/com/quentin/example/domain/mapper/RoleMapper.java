package com.quentin.example.domain.mapper;

import com.quentin.example.domain.RoleVO;
import com.quentin.example.config.basemapper.QuentinMapper;

import java.util.List;

/**
 * 角色Mapper
 *
 * @author Created by guoqun.yang
 * @version 1.0
 * @date Created in 15:47 2018/2/1
 */
public interface RoleMapper extends QuentinMapper<RoleVO> {
    List<RoleVO> queryRoleListWithSelected(Integer id);
}
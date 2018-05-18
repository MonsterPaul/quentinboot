package com.quentin.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quentin.example.domain.UserRoleVO;
import com.quentin.example.domain.UserVO;
import com.quentin.example.domain.mapper.UserRoleMapper;
import com.quentin.example.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * 用户信息服务层
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 17:05 2018/2/1
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<UserVO> implements IUserService<UserVO> {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserVO queryUserByName(String username) {
        Example example = new Example(UserVO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", username);

        List<UserVO> userVOS = selectByExample(example);
        if (null != userVOS && !userVOS.isEmpty()) {
            return userVOS.get(0);
        }

        return null;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void deleteUser(Long userid) {
        //删除用户表
        mapper.deleteByPrimaryKey(userid);
        //删除用户角色表
        Example example = new Example(UserRoleVO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userid);
        userRoleMapper.deleteByExample(example);
    }

    @Override
    public PageInfo<UserVO> selectByPage(UserVO user, int start, int length) {
        int page = start / length + 1;
        Example example = new Example(UserVO.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(user.getUserName())) {
            criteria.andLike("userName", "%" + user.getUserName() + "%");
        }
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        if (user.getEnable() != null) {
            criteria.andEqualTo("enable", user.getEnable());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<UserVO> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

}

package com.quentin.example.service;

import com.github.pagehelper.PageInfo;
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
public interface IUserService<T> {

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return T
     * @author guoqun.yang
     * @date 2018/4/25 22:31
     * @version 1.0
     */
    T queryUserByName(String username);

    /**
     * 根据ID删除用户、用户角色
     *
     * @param userid
     * @return void
     * @author guoqun.yang
     * @date 2018/4/25 22:19
     * @version 1.0
     */
    void deleteUser(Long userid);

    /**
     * 分页查询用户
     *
     * @param user
     * @param start
     * @param length
     * @return com.github.pagehelper.PageInfo<UserVO>
     * @author guoqun.yang
     * @date 2018/4/25 22:20
     * @version 1.0
     */
    PageInfo<T> selectByPage(T user, int start, int length);
}

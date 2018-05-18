package com.quentin.example.service;

import com.quentin.example.config.dynamicsdb.DataSource;
import com.quentin.example.config.dynamicsdb.DataSourceEnum;
import com.quentin.example.domain.UserVO;

import java.util.Set;

/**
 * 定义shirorealm所需数据的接口
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 13:36 2018/2/6
 * @Version 1.0
 */
@DataSource(name = DataSourceEnum.predb)
public interface IShiroService {

    /**
     * 根据账号信息获取用户
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/2/6 13:42
     * @version 1.0
     */
    UserVO getUserByAccount(String userName);

    /**
     * 获取授权资源信息
     *
     * @param userId
     * @Author: guoqun.yang
     * @Date: 2018/2/6 13:53
     * @version 1.0
     */
    Set<String> getMenus(Long userId);

    /**
     * 重新加载权限
     *
     * @param
     * @return void
     * @author guoqun.yang
     * @date 2018/4/25 22:35
     * @version 1.0
     */
    void updatePermission();

}

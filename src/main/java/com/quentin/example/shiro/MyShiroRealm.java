package com.quentin.example.shiro;

import com.google.common.collect.Maps;
import com.quentin.example.domain.ResourcesVO;
import com.quentin.example.domain.UserVO;
import com.quentin.example.service.IResourcesService;
import com.quentin.example.service.IShiroService;
import com.quentin.example.utils.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 安全实体数据源
 *
 * @Author Created by guoqun.yang
 * @Date Created in 15:47 2018/2/1
 * @Version 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private IResourcesService resourcesService;

    /**
     * 权限验证
     *
     * @param principalCollection
     * @Author: guoqun.yang
     * @Date: 2018/2/1 15:53
     * @version 1.0
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户ID
        UserVO user = (UserVO) SecurityUtils.getSubject().getPrincipal();
        Long userId = user.getId();

        //查询用户资源
        Map<String, Object> map = Maps.newHashMap();
        map.put("userid", userId);
        List<ResourcesVO> resourcesList = resourcesService.loadUserResources(map);

        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 注入权限
        for (ResourcesVO resources : resourcesList) {
            simpleAuthorizationInfo.addStringPermission(resources.getResUrl());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 身份验证
     *
     * @param authenticationToken
     * @Author: guoqun.yang
     * @Date: 2018/2/1 15:53
     * @version 1.0
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //token用户名
        String username = (String) authenticationToken.getPrincipal();
        //token密码
        String password = new String((char[]) authenticationToken.getCredentials());
        //获取shiroService服务类
        IShiroService shiroService = SpringContextHolder.getBean(IShiroService.class);
        //根据用户名获取用户信息
        UserVO user = shiroService.getUserByAccount(username);
        //账号为空
        if (null == user) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        // 账号锁定
        if (user.getEnable() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password.toCharArray(), getName());
        return simpleAuthenticationInfo;
    }
}

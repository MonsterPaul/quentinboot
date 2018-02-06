package com.quentin.example.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 安全实体数据源
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 15:47 2018/2/1
 * @Version 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

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
        return null;
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
        return null;
    }
}

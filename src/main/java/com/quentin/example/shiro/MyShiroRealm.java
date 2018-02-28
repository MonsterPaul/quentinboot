package com.quentin.example.shiro;

import com.quentin.example.domain.UserVO;
import com.quentin.example.service.IShiroService;
import com.quentin.example.utils.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;
import java.util.Set;

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
        //获取userId
        UserVO userVO = (UserVO) SecurityUtils.getSubject().getPrincipal();
        Long userId = userVO.getUserId();
        //获取shiroService服务类
        IShiroService shiroService = SpringContextHolder.getBean(IShiroService.class);
        //获取权限资源
        Set<String> perms = shiroService.getMenus(userId);
        //注入权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(perms);
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
        List<UserVO> ListUser = shiroService.getUserByAccount(username);
        //账号为空
        if (null == ListUser && ListUser.size() == 0) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        // 密码错误
        if (!password.equals(ListUser.get(0).getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        // 账号锁定
        if (ListUser.get(0).getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,getName());
        return simpleAuthenticationInfo;
    }
}

package com.quentin.example.service.impl;

import com.quentin.example.config.ShiroConfig;
import com.quentin.example.domain.UserVO;
import com.quentin.example.service.IShiroService;
import com.quentin.example.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * shiro 操作服务层
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 13:53 2018/2/6
 * @Version 1.0
 */
@Service
@Slf4j
public class ShiroServiceImpl implements IShiroService {

    @Autowired
    private IUserService userService;

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Override
    public UserVO getUserByAccount(String userName) {
        return (UserVO) userService.queryUserByName(userName);
    }

    @Override
    public Set<String> getMenus(Long userId) {
        //List<String> list = menuVOMapper.getMenuPermsByUserId(userId);
        List<String> list = new ArrayList<>();
        Set<String> permsSet = new HashSet<>();
        if (null != list && list.size() > 0) {
            for (String perm : list) {
                if (StringUtils.isNotBlank(perm)) {
                    permsSet.addAll(Arrays.asList(perm.trim().split(",")));
                }
            }
        }
        return permsSet;
    }

    @Override
    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                        .getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(ShiroConfig.getFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean
                    .getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }

            log.info("更新权限成功");
        }
    }

}

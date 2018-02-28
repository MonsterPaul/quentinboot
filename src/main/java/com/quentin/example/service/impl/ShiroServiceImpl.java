package com.quentin.example.service.impl;

import com.quentin.example.domain.UserVO;
import com.quentin.example.domain.mapper.MenuVOMapper;
import com.quentin.example.domain.mapper.UserVOMapper;
import com.quentin.example.service.IShiroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 13:53 2018/2/6
 * @Version 1.0
 */
@Service
public class ShiroServiceImpl implements IShiroService {

    @Autowired
    private UserVOMapper userVOMapper;
    @Autowired
    private MenuVOMapper menuVOMapper;

    @Override
    public List<UserVO> getUserByAccount(String userName) {
        return userVOMapper.queryByUserName(userName);
    }

    @Override
    public Set<String> getMenus(Long userId) {
        List<String> list = menuVOMapper.getMenuPermsByUserId(userId);
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
}

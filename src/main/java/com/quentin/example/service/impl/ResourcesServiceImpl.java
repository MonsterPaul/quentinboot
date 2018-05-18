package com.quentin.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quentin.example.domain.ResourcesVO;
import com.quentin.example.service.IResourcesService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 资源服务层实现类
 *
 * @author Created by guoqun.yang
 * @version 1.0
 * @date Created in 21:54 2018/4/25
 */
@Service
public class ResourcesServiceImpl extends BaseServiceImpl<ResourcesVO> implements IResourcesService {
    @Override
    public PageInfo<ResourcesVO> selectByPage(ResourcesVO resources, int start, int length) {
        int page = start/length+1;
        Example example = new Example(ResourcesVO.class);
        //分页查询
        PageHelper.startPage(page, length);
        List<ResourcesVO> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

    @Override
    public List<ResourcesVO> queryAll() {
        return null;
    }

    @Override
    public List<ResourcesVO> loadUserResources(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<ResourcesVO> queryResourcesListWithSelected(Integer rid) {
        return null;
    }
}

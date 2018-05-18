package com.quentin.example.service;

import com.github.pagehelper.PageInfo;
import com.quentin.example.domain.ResourcesVO;

import java.util.List;
import java.util.Map;

/**
 * 资源服务层
 *
 * @author Created by guoqun.yang
 * @date Created in 15:47 2018/2/1
 * @version 1.0
 */
public interface IResourcesService extends IBaseService<ResourcesVO> {


    /**
     * 分页查询资源
     *
     * @param resources
     * @param start
     * @param length
     * @return com.github.pagehelper.PageInfo<com.quentin.example.domain.ResourcesVO>
     * @author guoqun.yang
     * @date 2018/4/25 21:31
     * @version 1.0
     */
    PageInfo<ResourcesVO> selectByPage(ResourcesVO resources, int start, int length);


    /**
     * 查询全部资源
     *
     * @param
     * @return java.util.List<com.quentin.example.domain.ResourcesVO>
     * @author guoqun.yang
     * @date 2018/4/25 21:31
     * @version 1.0
     */
    List<ResourcesVO> queryAll();

    /**
     * 根据条件查询资源
     *
     * @param map
     * @return java.util.List<com.quentin.example.domain.ResourcesVO>
     * @author guoqun.yang
     * @date 2018/4/25 21:32
     * @version 1.0
     */
    List<ResourcesVO> loadUserResources(Map<String, Object> map);

    /**
     * 根据ID查询资源
     *
     * @param rid
     * @return java.util.List<com.quentin.example.domain.ResourcesVO>
     * @author guoqun.yang
     * @date 2018/4/25 21:32
     * @version 1.0
     */
    List<ResourcesVO> queryResourcesListWithSelected(Integer rid);
}

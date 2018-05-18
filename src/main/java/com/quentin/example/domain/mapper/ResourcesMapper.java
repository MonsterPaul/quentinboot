package com.quentin.example.domain.mapper;

import com.quentin.example.domain.ResourcesVO;
import com.quentin.example.config.basemapper.QuentinMapper;

import java.util.List;
import java.util.Map;

/**
 * 资源Mapper
 *
 * @author Created by guoqun.yang
 * @version 1.0
 * @date Created in 15:47 2018/2/1
 */
public interface ResourcesMapper extends QuentinMapper<ResourcesVO> {
    List<ResourcesVO> queryAll();

    List<ResourcesVO> loadUserResources(Map<String, Object> map);

    List<ResourcesVO> queryResourcesListWithSelected(Integer rid);
}
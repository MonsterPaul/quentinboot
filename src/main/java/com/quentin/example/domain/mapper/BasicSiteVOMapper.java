package com.quentin.example.domain.mapper;

import com.quentin.example.domain.BasicSiteVO;

public interface BasicSiteVOMapper {
    int insert(BasicSiteVO record);

    int insertSelective(BasicSiteVO record);

    BasicSiteVO selectByCode(String siteCode);

    int deleteById(Long id);

}
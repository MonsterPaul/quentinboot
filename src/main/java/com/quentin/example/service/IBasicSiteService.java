package com.quentin.example.service;

import com.quentin.example.config.dynamicsdb.DataSource;
import com.quentin.example.config.dynamicsdb.DataSourceEnum;
import com.quentin.example.domain.BasicSiteVO;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 9:49 2018/1/26
 * @Version 1.0
 */
@DataSource(name = DataSourceEnum.predb)
public interface IBasicSiteService {

    /**
     * 根据ID查询网点
     * @Author: guoqun.yang
     * @Date:   2018/1/26 9:50
     * @param
     * @version 1.0
     */
    BasicSiteVO selectBasicSiteBySiteCode(String siteCode);
}

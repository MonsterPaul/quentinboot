package com.quentin.example.service;

import com.quentin.example.config.dynamicsdb.DataSource;
import com.quentin.example.config.dynamicsdb.DataSourceEnum;
import com.quentin.example.domain.OptEwbVO;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 17:07 2018/1/25
 * @Version 1.0
 */
@DataSource(name = DataSourceEnum.lbdb)
public interface IEwbService {

    OptEwbVO getEwb(String ewbNo);

}

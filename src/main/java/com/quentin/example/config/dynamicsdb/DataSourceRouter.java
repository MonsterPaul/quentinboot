package com.quentin.example.config.dynamicsdb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 13:35 2017/12/18
 * @Version 1.0
 */
@Slf4j
public class DataSourceRouter extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("-------------数据源名称-----------------" + DataSourceHolder.getCurrentDS().name());
        return DataSourceHolder.getCurrentDS();
    }
}

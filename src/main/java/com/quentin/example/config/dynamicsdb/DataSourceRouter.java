package com.quentin.example.config.dynamicsdb;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 13:35 2017/12/18
 * @Version 1.0
 */
public class DataSourceRouter extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getCurrentDS();
    }
}

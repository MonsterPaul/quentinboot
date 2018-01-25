package com.quentin.example.config.dynamicsdb;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 第一个数据源配置
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 16:03 2018/1/25
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@Slf4j
public class DruidDataSourceConfig {

    @Value("${mybatis.mapper-locations}")
    private String masterMapperLocations;

    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean(name = "masterDataSource", destroyMethod = "close", initMethod = "init")
    @Primary
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    @ConfigurationProperties(prefix = "spring.datasource.second")
    @Bean(name = "secondDataSource", destroyMethod = "close", initMethod = "init")
    public DataSource secondDataSource() {
        return new DruidDataSource();
    }

    /**
     * SqlSessionFactory配置
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/1/25 16:11
     * @version 1.0
     */
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(masterMapperLocations));
        log.info("-------------数据源名称-----------------" + roundRobinDataSouceProxy().getClass().getName());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /**
     * 封装所有数据源
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/1/25 17:05
     * @version 1.0
     */
    @Bean("dataSources")
    public Map<DataSourceEnum, DataSource> dataSources() {
        Map<DataSourceEnum, DataSource> dataSources = new HashMap<>();
        dataSources.put(DataSourceEnum.lbdb, masterDataSource());
        dataSources.put(DataSourceEnum.predb, secondDataSource());
        return dataSources;
    }

    /**
     * 设置数据源
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/1/25 17:05
     * @version 1.0
     */
    @Bean(name = "routingDataSource")
    public DataSourceRouter roundRobinDataSouceProxy() {
        DataSourceRouter proxy = new DataSourceRouter();

        Map<Object, Object> targetDataSources = new HashMap<>();
        for (DataSourceEnum type : dataSources().keySet()) {
            targetDataSources.put(type, dataSources().get(type));
        }
        proxy.setDefaultTargetDataSource(masterDataSource());//默认lb库

        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }


    /**
     * 配置事物管理器
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/1/25 16:11
     * @version 1.0
     */
    @Bean(name = "secondTransactionManager")
    @Primary
    public DataSourceTransactionManager secondTransactionManager() {
        return new DataSourceTransactionManager(roundRobinDataSouceProxy());
    }
}

package com.quentin.example.config.jediscluster;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * jedis配置信息类
 * Created by GUOQUN.YANG.
 * Date: 2018/1/27.
 * Time: 21:33.
 * Description:
 */
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class JedisProperties {

    /**
     * jedis-cluster 所有节点
     */
    private String nodes;

    /**
     * 默认连接超时时间
     */
    private int connectionTimeout = 2000;
    /**
     * 默认读取数据超时时间
     */
    private int soTimeout = 3000;
    /**
     * 出现异常最大重试次数
     */
    private int maxRedirections = 5;

    /**
     * 最大连接数
     */
    @Value("${spring.redis.cluster.pool.maxTotal}")
    private int maxTotal;
    /**
     * 最大空闲连接数
     */
    @Value("${spring.redis.cluster.pool.maxIdle}")
    private int maxIdle;
    /**
     * 最小空闲连接数, 默认0
     */
    @Value("${spring.redis.cluster.pool.minIdle}")
    private int minIdle;
    /**
     * 获取连接时的最大等待毫秒数
     */
    @Value("${spring.redis.cluster.pool.maxWaitMillis}")
    private int maxWaitMillis;

}

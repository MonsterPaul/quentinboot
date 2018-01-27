package com.quentin.example.config.jediscluster;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * jedis-cluster工厂类
 * Created by GUOQUN.YANG.
 * Date: 2018/1/27.
 * Time: 18:39.
 * Description:
 */
@Configuration
@EnableConfigurationProperties(JedisProperties.class)
@Slf4j
public class JedisClusterConfiguration extends CachingConfigurerSupport {

    @Autowired
    private JedisProperties jedisProperties;

    @Bean
    public JedisCluster JedisClusterFactory() throws ParseException {

        /**
         * 获取所有节点host和port
         */
        String[] split = jedisProperties.getNodes().split(",");
        Set<String> hosts = Sets.newHashSet();
        Collections.addAll(hosts, split);

        Set<HostAndPort> haps = new HashSet<>();
        for (String node : hosts) {
            String[] arr = node.split(":");
            if (arr.length != 2) {
                log.error("提示：Please check the nodes format");
                throw new ParseException("node address error !", node.length() - 1);
            }
            haps.add(new HostAndPort(arr[0], Integer.valueOf(arr[1])));
        }

        /**
         * jedis对象池
         */
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(jedisProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(jedisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(jedisProperties.getMaxWaitMillis());
        jedisPoolConfig.setMinIdle(jedisProperties.getMinIdle());

        return new JedisCluster(haps, jedisProperties.getConnectionTimeout(), jedisProperties.getSoTimeout(), jedisProperties.getMaxRedirections(), jedisPoolConfig);
    }


}

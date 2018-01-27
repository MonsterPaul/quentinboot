package com.quentin.example;

import com.google.common.collect.Maps;
import com.quentin.example.service.IJedisHelperService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * jediscluster测试类
 * Created by GUOQUN.YANG.
 * Date: 2018/1/27.
 * Time: 20:19.
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestJedisCluster {
    @Autowired
    private IJedisHelperService jedisHelperService;

    private static final String MAP_KEY = "quentin_info";

    @Test
    public void testSetMap() {
        Map map = Maps.newHashMap();
        map.put("name", "杨国群");
        map.put("age", "21");
        map.put("sex", "男");
        jedisHelperService.putMapCache(MAP_KEY, map);
    }

    @Test
    public void getKey() {
        String name = (String) jedisHelperService.getMapValueCache(MAP_KEY,"name");
        log.info("=========获取到信息为========="+null == name ? "" : name);
    }

}

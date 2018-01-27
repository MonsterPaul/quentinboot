package com.quentin.example.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.quentin.example.service.IJedisHelperService;
import com.quentin.example.utils.SerializingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jediscluster使用工具类服务层
 * Created by GUOQUN.YANG.
 * Date: 2018/1/27.
 * Time: 20:09.
 * Description:
 */
@Service
public class JedisHelperService implements IJedisHelperService {

    private static final String JEDIS_SET_RETURN_OK = "OK";

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public Object getCache(Serializable cacheKey) {
        return SerializingUtil.deserialize(jedisCluster.get(SerializingUtil.serialize(cacheKey)));
    }

    @Override
    public boolean putCache(Serializable cacheKey, Object objValue, int expiration) {
        String result = jedisCluster.setex(SerializingUtil.serialize(cacheKey), expiration, SerializingUtil.serialize(objValue));
        return StringUtils.equals(JEDIS_SET_RETURN_OK, result);
    }

    @Override
    public Long removeCache(Serializable cacheKey) {
        return jedisCluster.del(SerializingUtil.serialize(cacheKey));
    }

    @Override
    public boolean putListCache(Serializable cacheKey, Object objValue) {
        Long num = jedisCluster.rpush(SerializingUtil.serialize(cacheKey), SerializingUtil.serialize(objValue));
        return num > 0;
    }

    @Override
    public boolean putListCache(Serializable cacheKey, Object objValue, int index) {
        String result = jedisCluster.lset(SerializingUtil.serialize(cacheKey), index, SerializingUtil.serialize(objValue));
        return StringUtils.equals(JEDIS_SET_RETURN_OK, result);
    }

    @Override
    public List<Object> getListCache(Serializable cacheKey, int start, int end) {
        List<byte[]> list = jedisCluster.lrange(SerializingUtil.serialize(cacheKey), start, end);
        if (null != list && list.size() > 0) {
            List<Object> objList = new ArrayList<>();
            for (byte[] b : list) {
                objList.add(SerializingUtil.deserialize(b));
            }
            return objList;
        }
        return null;
    }

    @Override
    public List<Object> getListCache(Serializable cacheKey) {
        return getListCache(cacheKey, 0, -1);
    }

    @Override
    public boolean trimListCache(Serializable cacheKey, int start, int end) {
        String result = jedisCluster.ltrim(SerializingUtil.serialize(cacheKey), start, end);
        return StringUtils.equals(JEDIS_SET_RETURN_OK, result);
    }

    @Override
    public boolean putMapCache(Serializable cacheKey, Map<Object, Object> map) {
        if (null != map && !map.isEmpty()) {
            Map<byte[], byte[]> byteMap = new HashMap<>();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                byteMap.put(SerializingUtil.serialize(entry.getKey()), SerializingUtil.serialize(entry.getValue()));
            }
            jedisCluster.hmset(SerializingUtil.serialize(cacheKey), byteMap);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMapCache(Serializable cacheKey, Serializable mapKey) {
        Long result = jedisCluster.hdel(SerializingUtil.serialize(cacheKey), SerializingUtil.serialize(mapKey));
        return result > 0;
    }


    @Override
    public Object getMapValueCache(Serializable cacheKey, Serializable mapKey) {
        List<byte[]> list = jedisCluster.hmget(SerializingUtil.serialize(cacheKey), SerializingUtil.serialize(mapKey));
        if (null != list && list.size() > 0) {
            return SerializingUtil.deserialize(list.get(0));
        }
        return null;
    }
}

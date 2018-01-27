package com.quentin.example.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * jediscluster使用服务层
 * Created by GUOQUN.YANG.
 * Date: 2018/1/27.
 * Time: 20:09.
 * Description:
 */
public interface IJedisHelperService {

    /**
     * 根据缓存key获取值
     *
     * @param cacheKey
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:20
     * @version 1.0
     */
    Object getCache(Serializable cacheKey);

    /**
     * 设置缓存数据的key-value，并设置失效时间，单位为秒
     *
     * @param cacheKey
     * @param objValue
     * @param expiration
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:20
     * @version 1.0
     */
    boolean putCache(Serializable cacheKey, Object objValue, int expiration);

    /**
     * 清除缓存
     *
     * @param cacheKey
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:21
     * @version 1.0
     */
    Long removeCache(Serializable cacheKey);

    /**
     * 向指定list集合中添加对象，在list尾部添加对象
     *
     * @param cacheKey
     * @param objValue
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:21
     * @version 1.0
     */
    boolean putListCache(Serializable cacheKey, Object objValue);

    /**
     * 向指定list集合中添加对象，并指定位置坐标
     *
     * @param cacheKey
     * @param objValue
     * @param index
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:21
     * @version 1.0
     */
    boolean putListCache(Serializable cacheKey, Object objValue, int index);

    /**
     * 根据坐标，返回一段集合
     *
     * @param cacheKey
     * @param start    起始坐标 头部为0
     * @param end      结束坐标 尾部为-1
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:21
     * @version 1.0
     */
    List<Object> getListCache(Serializable cacheKey, int start, int end);

    /**
     * 返回结合
     *
     * @param cacheKey
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:22
     * @version 1.0
     */
    List<Object> getListCache(Serializable cacheKey);

    /**
     * 裁剪list集合
     *
     * @param cacheKey
     * @param start    起始坐标
     * @param end      结束坐标
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:22
     * @version 1.0
     */
    boolean trimListCache(Serializable cacheKey, int start, int end);

    /**
     * 添加map集合
     *
     * @param cacheKey
     * @param map
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:22
     * @version 1.0
     */
    boolean putMapCache(Serializable cacheKey, Map<Object, Object> map);

    /**
     * 删除map中的键值
     *
     * @param cacheKey
     * @param mapKey
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:23
     * @version 1.0
     */
    boolean deleteMapCache(Serializable cacheKey, Serializable mapKey);


    /**
     * 获取map中的值
     *
     * @param cacheKey
     * @param mapKey
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:23
     * @version 1.0
     */
    Object getMapValueCache(Serializable cacheKey, Serializable mapKey);
}

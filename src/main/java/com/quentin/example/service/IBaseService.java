package com.quentin.example.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用接口
 *
 * @version 1.0
 * @auth Created by guoqun.yang
 * @date Created in 16:13 2018/2/1
 */
@Service
public interface IBaseService<T> {

    /**
     * 根据主键查询
     *
     * @param key
     * @return T
     * @author guoqun.yang
     * @date 2018/4/25 21:50
     * @version 1.0
     */
    T selectByKey(Object key);

    /**
     * 保存
     *
     * @param entity
     * @return int
     * @author guoqun.yang
     * @date 2018/4/25 21:50
     * @version 1.0
     */
    int save(T entity);

    /**
     * 删除
     *
     * @param key
     * @return int
     * @author guoqun.yang
     * @date 2018/4/25 21:50
     * @version 1.0
     */
    int delete(Object key);

    /**
     * 修改整个对象
     *
     * @param entity
     * @return int
     * @author guoqun.yang
     * @date 2018/4/25 21:51
     * @version 1.0
     */
    int updateAll(T entity);

    /**
     * 修改不为空的对象
     *
     * @param entity
     * @return int
     * @author guoqun.yang
     * @date 2018/4/25 21:51
     * @version 1.0
     */
    int updateNotNull(T entity);

    /**
     * 动态查询
     *
     * @param example
     * @return java.util.List<T>
     * @author guoqun.yang
     * @date 2018/4/25 21:51
     * @version 1.0
     */
    List<T> selectByExample(Object example);
}

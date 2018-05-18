package com.quentin.example.config.basemapper;

/**
 * 基础Mapper通用类
 * 特别注意，该接口不能被扫描到，否则会出错
 *
 * @Author Created by guoqun.yang
 * @Date Created in 15:47 2018/2/1
 * @Version 1.0
 */

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface QuentinMapper<T> extends Mapper<T>, MySqlMapper<T> {
}


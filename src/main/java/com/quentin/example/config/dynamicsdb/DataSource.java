package com.quentin.example.config.dynamicsdb;

import java.lang.annotation.*;

/**
 * 自定义数据源类型注解
 * @Auth Created by guoqun.yang
 * @Date Created in 11:29 2017/12/19
 * @Version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface DataSource {
    DataSourceEnum name() default DataSourceEnum.predb;
}

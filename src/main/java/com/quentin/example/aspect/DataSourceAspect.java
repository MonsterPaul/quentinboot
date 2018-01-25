package com.quentin.example.aspect;

import com.quentin.example.config.dynamicsdb.DataSource;
import com.quentin.example.config.dynamicsdb.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * aop动态设置数据源对象
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 11:18 2017/12/19
 * @Version 1.0
 */
@Component
@Aspect
@Order(-1)
@Slf4j
public class DataSourceAspect {

    @Pointcut("execution(public * com.quentin.example.service..*.*(..))")
    public void appDataSource() {
    }


    @Before("appDataSource()")
    public void beforeAdvice(JoinPoint jp) {
        try {
            MethodSignature methodSignature = (MethodSignature) jp.getSignature();
            Class targetClass = jp.getTarget().getClass();
            // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
            for (Class<?> clazz : targetClass.getInterfaces()) {
                resolveDataSource(clazz, methodSignature.getMethod());
            }
            resolveDataSource(targetClass, methodSignature.getMethod());
        } catch (Exception e) {
            log.error("数据源切换发生异常：", e);
        }
    }

    /**
     * 获取目标对象类型（方法或类）
     *
     * @param clazz
     * @param method
     * @Author: guoqun.yang
     * @Date: 2018/1/25 16:48
     * @version 1.0
     */
    public void resolveDataSource(Class<?> clazz, Method method) {
        try {
            //类注解
            Class<?>[] types = method.getParameterTypes();
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource ds = clazz.getAnnotation(DataSource.class);
                DataSourceHolder.setDS(ds.name());
            }
            //方法注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m.isAnnotationPresent(DataSource.class)) {
                DataSource ds = method.getAnnotation(DataSource.class);
                DataSourceHolder.setDS(ds.name());
            }
        } catch (NoSuchMethodException e) {
            log.error("DataSourceAspect.resolveDataSource获取目标对象数据源标识发生异常：", e);
        }
    }

}

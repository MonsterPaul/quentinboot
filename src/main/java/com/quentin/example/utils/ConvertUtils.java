package com.quentin.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 格式转换工具类
 *
 * @Auth Created by 杨国群
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
@Slf4j
public class ConvertUtils {

    /**
     * 字符串转换为int
     *
     * @param str
     * @param defaultValue 默认值
     * @Author: 杨国群
     * @Date: 2017/11/15 19:49
     * @version 1.0
     */
    public static int strToInt(String str, int defaultValue) {
        try {
            defaultValue = Integer.parseInt(str);
        } catch (Exception e) {
            log.error("[ConvertUtils.strToInt:转换异常:]", e);
        }
        return defaultValue;
    }

    /**
     * String转换为long
     *
     * @param str
     * @param defaultValue 默认值
     * @Author: 杨国群
     * @Date: 2017/11/15 19:52
     * @version 1.0
     */
    public static long strToLong(String str, long defaultValue) {
        try {
            defaultValue = Long.parseLong(str);
        } catch (Exception e) {
            log.error("[ConvertUtils.strToLong:转换异常:]", e);
        }
        return defaultValue;
    }

    /**
     * 字符串转换为float
     *
     * @param str
     * @param defaultValue 默认值
     * @Author: 杨国群
     * @Date: 2017/11/15 19:54
     * @version 1.0
     */
    public static float strToFloat(String str, float defaultValue) {
        try {
            defaultValue = Float.parseFloat(str);
        } catch (Exception e) {
            log.error("[ConvertUtils.strToFloat:转换异常:]", e);
        }
        return defaultValue;
    }

    /**
     * String转换为Double
     *
     * @param str
     * @param defaultValue 默认值
     * @Author: 杨国群
     * @Date: 2017/11/15 20:46
     * @version 1.0
     */
    public static double strToDouble(String str, double defaultValue) {
        try {
            defaultValue = Double.parseDouble(str);
        } catch (Exception e) {
            log.error("[ConvertUtils.strToDouble:转换异常:]", e);
        }
        return defaultValue;
    }

    /**
     * 字符串转换日期
     *
     * @param str
     * @param defaultValue
     * @Author: 杨国群
     * @Date: 2017/11/15 20:46
     * @version 1.0
     */
    public static Date strToDate(String str, Date defaultValue) {
        return strToDate(str, "yyyy-MM-dd HH:mm:ss", defaultValue);
    }

    /**
     * 字符串转换为指定格式的日期
     *
     * @param str
     * @param format
     * @param defaultValue
     * @Author: 杨国群
     * @Date: 2017/11/15 20:50
     * @version 1.0
     */
    public static Date strToDate(String str, String format, Date defaultValue) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        try {
            defaultValue = fmt.parse(str);
        } catch (Exception e) {
            log.error("[ConvertUtils.strToDate:转换异常:]", e);
        }
        return defaultValue;
    }

    /**
     * 日期转换为字符串
     *
     * @param date
     * @param defaultValue
     * @Author: 杨国群
     * @Date: 2017/11/15 20:50
     * @version 1.0
     */
    public static String dateToStr(Date date, String defaultValue) {
        return dateToStr(date, "yyyy-MM-dd HH:mm:ss", defaultValue);
    }

    /**
     * 日期转换为指定格式的字符串
     *
     * @param date
     * @param format
     * @param defaultValue
     * @Author: 杨国群
     * @Date: 2017/11/15 20:51
     * @version 1.0
     */
    public static String dateToStr(Date date, String format, String defaultValue) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            defaultValue = sdf.format(date);
        } catch (Exception e) {
            log.error("[ConvertUtils.dateToStr:转换异常:]", e);
        }
        return defaultValue;
    }


    /**
     * util date 转换为 sqldate
     *
     * @param date
     * @return
     * @author:chenssy
     * @date : 2016年5月21日 上午10:30:09
     */
    public static java.sql.Date dateToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * sql date 转换为 util date
     *
     * @param date
     * @return
     * @author:chenssy
     * @date : 2016年5月21日 上午10:30:26
     */
    public static Date sqlDateToDate(java.sql.Date date) {
        return new Date(date.getTime());
    }

    /**
     * date 转换为 timestamp
     *
     * @param date
     * @Author: 杨国群
     * @Date: 2017/11/15 20:53
     * @version 1.0
     */
    public static Timestamp dateToSqlTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * timestamp 转换为date
     *
     * @param date
     * @Author: 杨国群
     * @Date: 2017/11/15 20:53
     * @version 1.0
     */
    public static Date qlTimestampToDate(Timestamp date) {
        return new Date(date.getTime());
    }

    /**
     * Bean转换为Map
     *
     * @param object
     * @return String-Object的HashMap
     * @Author: 杨国群
     * @Date: 2017/11/15 21:13
     * @version 1.0
     */
    public static Map<String, Object> bean2MapObject(Object object) {
        if (object == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}

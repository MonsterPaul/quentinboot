package com.quentin.example.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Java反射工具类
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
public class ReflectUtils {

    /**
     * 获取成员变量的修饰符
     * PUBLIC: 1
     * PRIVATE: 2
     * PROTECTED: 4
     * STATIC: 8
     * FINAL: 16
     * SYNCHRONIZED: 32
     * VOLATILE: 64
     * TRANSIENT: 128
     * NATIVE: 256
     * INTERFACE: 512
     * ABSTRACT: 1024
     * STRICT: 2048
     *
     * @param clazz
     * @param field
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:14
     * @version 1.0
     */
    public static <T> int getFieldModifier(Class<T> clazz, String field) throws Exception {
        // getDeclaredFields可以获取所有修饰符的成员变量，包括private,protected等getFields则不可以
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(field)) {
                return fields[i].getModifiers();
            }
        }
        throw new Exception(clazz + " has no field \"" + field + "\"");
    }

    /**
     * 获取成员方法的修饰符
     * PUBLIC: 1
     * PRIVATE: 2
     * PROTECTED: 4
     * STATIC: 8
     * FINAL: 16
     * SYNCHRONIZED: 32
     * VOLATILE: 64
     * TRANSIENT: 128
     * NATIVE: 256
     * INTERFACE: 512
     * ABSTRACT: 1024
     * STRICT: 2048
     *
     * @param clazz
     * @param method
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:15
     * @version 1.0
     */
    public static <T> int getMethodModifier(Class<T> clazz, String method) throws Exception {

        // getDeclaredMethods可以获取所有修饰符的成员方法，包括private,protected等getMethods则不可以
        Method[] m = clazz.getDeclaredMethods();

        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().equals(method)) {
                return m[i].getModifiers();
            }
        }
        throw new Exception(clazz + " has no method \"" + method + "\"");
    }

    /**
     * [对象]根据成员变量名称获取其值
     *
     * @param clazzInstance
     * @param field
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:15
     * @version 1.0
     */
    public static <T> Object getFieldValue(Object clazzInstance, Object field) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        Field[] fields = clazzInstance.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(field)) {
                // 对于私有变量的访问权限，在这里设置，这样即可访问Private修饰的变量
                fields[i].setAccessible(true);
                return fields[i].get(clazzInstance);
            }
        }

        return null;
    }

    /**
     * [类]根据成员变量名称获取其值（默认值）
     *
     * @param clazz
     * @param field
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:16
     * @version 1.0
     */
    public static <T> Object getFieldValue(Class<T> clazz, String field) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {

        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(field)) {
                // 对于私有变量的访问权限，在这里设置，这样即可访问Private修饰的变量
                fields[i].setAccessible(true);
                return fields[i].get(clazz.newInstance());
            }
        }

        return null;
    }

    /**
     * 获取所有的成员变量(通过GET，SET方法获取)
     *
     * @param clazz
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:16
     * @version 1.0
     */
    public static <T> String[] getFields(Class<T> clazz) {

        Field[] fields = clazz.getDeclaredFields();

        String[] fieldsArray = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fieldsArray[i] = fields[i].getName();
        }

        return fieldsArray;
    }

    /**
     * 获取所有的成员变量,包括父类
     *
     * @param clazz
     * @param superClass 是否包括父类
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:17
     * @version 1.0
     */
    public static <T> Field[] getFields(Class<T> clazz, boolean superClass) throws Exception {

        Field[] fields = clazz.getDeclaredFields();
        Field[] superFields = null;
        if (superClass) {
            Class superClazz = clazz.getSuperclass();
            if (superClazz != null) {
                superFields = superClazz.getDeclaredFields();
            }
        }

        Field[] allFields = null;

        if (superFields == null || superFields.length == 0) {
            allFields = fields;
        } else {
            allFields = new Field[fields.length + superFields.length];
            for (int i = 0; i < fields.length; i++) {
                allFields[i] = fields[i];
            }
            for (int i = 0; i < superFields.length; i++) {
                allFields[fields.length + i] = superFields[i];
            }
        }

        return allFields;
    }

    /**
     * 获取所有的成员变量,包括父类
     *
     * @param clazz
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:17
     * @version 1.0
     */
    public static <T> Field[] getClassFieldsAndSuperClassFields(Class<T> clazz) throws Exception {

        Field[] fields = clazz.getDeclaredFields();

        if (clazz.getSuperclass() == null) {
            throw new Exception(clazz.getName() + "没有父类");
        }

        Field[] superFields = clazz.getSuperclass().getDeclaredFields();

        Field[] allFields = new Field[fields.length + superFields.length];

        for (int i = 0; i < fields.length; i++) {
            allFields[i] = fields[i];
        }
        for (int i = 0; i < superFields.length; i++) {
            allFields[fields.length + i] = superFields[i];
        }

        return allFields;
    }

    /**
     * 指定类，调用指定的无参方法
     *
     * @param clazz
     * @param method
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:18
     * @version 1.0
     */
    public static <T> Object invoke(Class<T> clazz, String method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        Object instance = clazz.newInstance();
        Method m = clazz.getMethod(method, new Class[]{});
        return m.invoke(instance, new Object[]{});
    }

    /**
     * 通过对象，访问其方法
     *
     * @param clazzInstance
     * @param method
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:19
     * @version 1.0
     */
    public static <T> Object invoke(Object clazzInstance, String method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        Method m = clazzInstance.getClass().getMethod(method, new Class[]{});
        return m.invoke(clazzInstance, new Object[]{});
    }

    /**
     * 指定类，调用指定的方法
     *
     * @param clazz
     * @param method
     * @param paramClasses
     * @param params
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:19
     * @version 1.0
     */
    public static <T> Object invoke(Class<T> clazz, String method, Class<T>[] paramClasses, Object[] params) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Object instance = clazz.newInstance();
        Method _m = clazz.getMethod(method, paramClasses);
        return _m.invoke(instance, params);
    }

    /**
     * 通过类的实例，调用指定的方法
     *
     * @param clazzInstance
     * @param method
     * @param paramClasses
     * @param params
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:20
     * @version 1.0
     */
    public static <T> Object invoke(Object clazzInstance, String method, Class<T>[] paramClasses, Object[] params) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Method _m = clazzInstance.getClass().getMethod(method, paramClasses);
        return _m.invoke(clazzInstance, params);
    }

}

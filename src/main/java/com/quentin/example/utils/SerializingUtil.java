package com.quentin.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 序列化工具类，负责byte[]和Object之间的相互转换
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
@Slf4j
public class SerializingUtil {

    /**
     * 对实体Bean进行序列化操作
     *
     * @param source 待转换的实体
     * @return 转换之后的字节数组
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:12
     * @version 1.0
     */
    public static byte[] serialize(Object source) {
        ByteArrayOutputStream byteOut = null;
        ObjectOutputStream ObjOut = null;
        try {
            byteOut = new ByteArrayOutputStream();
            ObjOut = new ObjectOutputStream(byteOut);
            ObjOut.writeObject(source);
            ObjOut.flush();
        } catch (IOException e) {
            log.error(source.getClass().getName() + " serialized error !", e);
        } finally {
            try {
                if (null != ObjOut) {
                    ObjOut.close();
                }
            } catch (IOException e) {
                ObjOut = null;
            }
        }
        return byteOut.toByteArray();
    }

    /**
     * 将字节数组反序列化为实体Bean
     *
     * @param source 需要进行反序列化的字节数组
     * @return 反序列化后的实体Bean
     * @Author: guoqun.yang
     * @Date: 2018/1/27 20:13
     * @version 1.0
     */
    public static Object deserialize(byte[] source) {
        ObjectInputStream ObjIn = null;
        Object retVal = null;
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(source);
            ObjIn = new ObjectInputStream(byteIn);
            retVal = ObjIn.readObject();
        } catch (Exception e) {
            log.error("deserialized error  !", e);
        } finally {
            try {
                if (null != ObjIn) {
                    ObjIn.close();
                }
            } catch (IOException e) {
                ObjIn = null;
            }
        }
        return retVal;
    }
}
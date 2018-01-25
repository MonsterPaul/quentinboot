package com.quentin.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Collection;

/**
 * 克隆工具类，进行深克隆,包括对象、集合
 *
 * @Auth Created by 杨国群
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
@Slf4j
public class CloneUtils {

    /**
     * 采用对象的序列化完成对象的深克隆
     *
     * @param obj
     * @Author: 杨国群
     * @Date: 2017/11/15 21:16
     * @version 1.0
     */
    public static <T extends Serializable> T cloneObject(T obj) {
        T cloneObj = null;
        try {
            // 写入字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();

            // 分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            // 返回生成的新对象
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            log.error("[CloneUtils.cloneObject:]", e);
        }
        return cloneObj;
    }

    /**
     * 利用序列化完成集合的深克隆
     *
     * @param collection
     * @Author: 杨国群
     * @Date: 2017/11/15 21:18
     * @version 1.0
     */
    public static <T> Collection<T> cloneCollection(Collection<T> collection) throws ClassNotFoundException, IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(collection);
        out.close();

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Collection<T> dest = (Collection<T>) in.readObject();
        in.close();

        return dest;
    }
}

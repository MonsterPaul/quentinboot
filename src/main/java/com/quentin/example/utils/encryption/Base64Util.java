package com.quentin.example.utils.encryption;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * BASE64算法实现加解密
 *
 * @Author Created by guoqun.yang
 * @Date Created in 14:24 2017/12/27
 * @Version 1.0
 */
public class Base64Util {

    /**
     * base64算法加密
     *
     * @author guoqun.yang
     * @date   2018/5/18 23:06
     * @param   data
     * @return java.lang.String
     * @version 1.0
     */
    public static String base64Encrypt(byte[] data) {
        String result = new BASE64Encoder().encode(data);
        return result;
    }

    /**
     * base64算法解密
     *
     * @author guoqun.yang
     * @date   2018/5/18 23:06
     * @param   data
     * @return java.lang.String
     * @version 1.0
     */
    public static String base64Decrypt(String data) throws Exception {
        byte[] resultBytes = new BASE64Decoder().decodeBuffer(data);
        return new String(resultBytes);
    }
}

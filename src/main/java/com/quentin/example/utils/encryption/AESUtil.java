package com.quentin.example.utils.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES算法编程实现
 *
 * @Author Created by guoqun.yang
 * @Date Created in 14:24 2017/12/27
 * @Version 1.0
 */
public class AESUtil {

    /**
     * 生成密钥
     *
     * @author guoqun.yang
     * @date   2018/5/18 23:09
     * @param
     * @return byte[]
     * @version 1.0
     */
    public static byte[] initKey() throws Exception {
        //密钥生成器
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        //初始化密钥生成器
        //默认128，获得无政策权限后可用192或256
        keyGen.init(128);
        //生成密钥
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 加密
     *
     * @author guoqun.yang
     * @date   2018/5/18 23:09
     * @param   data
     * @param   key
     * @return byte[]
     * @version 1.0
     */
    public static byte[] encryptAES(byte[] data, byte[] key) throws Exception {
        //恢复密钥
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        //Cipher完成加密
        Cipher cipher = Cipher.getInstance("AES");
        //根据密钥对cipher进行初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //加密
        byte[] encrypt = cipher.doFinal(data);

        return encrypt;
    }

    /**
     * AES解密
     *
     * @param data
     * @param key
     * @return byte[]
     * @author guoqun.yang
     * @date 2018/5/18 22:59
     * @version 1.0
     */
    public static byte[] decryptAES(byte[] data, byte[] key) throws Exception {
        //恢复密钥生成器
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        //Cipher完成解密
        Cipher cipher = Cipher.getInstance("AES");
        //根据密钥对cipher进行初始化
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plain = cipher.doFinal(data);
        return plain;
    }
}

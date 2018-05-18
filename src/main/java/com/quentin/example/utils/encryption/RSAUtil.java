package com.quentin.example.utils.encryption;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 非对称RSA加解密
 *
 * @Author Created by guoqun.yang
 * @Date Created in 14:24 2017/12/27
 * @Version 1.0
 */
public class RSAUtil {
    /**
     * 公钥KEY
     */
    public static final String PUBLIC_KEY = "RSAPublicKey";
    /**
     * 私钥KEY
     */
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 生成RSA的公钥和私钥
     *
     * @author guoqun.yang
     * @date   2018/5/18 23:02
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @version 1.0
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //512-65536 & 64的倍数
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 获得公钥
     *
     * @author guoqun.yang
     * @date   2018/5/18 23:03
     * @param   keyMap
     * @return java.security.interfaces.RSAPublicKey
     * @version 1.0
     */
    public static RSAPublicKey getpublicKey(Map<String, Object> keyMap) {
        RSAPublicKey publicKey = (RSAPublicKey) keyMap.get(PUBLIC_KEY);
        return publicKey;
    }

    /**
     * 获得私钥
     *
     * @author guoqun.yang
     * @date   2018/5/18 23:04
     * @param   keyMap
     * @return java.security.interfaces.RSAPrivateKey
     * @version 1.0
     */
    public static RSAPrivateKey getPrivateKey(Map<String, Object> keyMap) {
        RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get(PRIVATE_KEY);
        return privateKey;
    }

    /**
     * 公钥加密
     *
     * @author guoqun.yang
     * @date   2018/5/18 23:05
     * @param   data
     * @param   publicKey
     * @return byte[]
     * @version 1.0
     */
    public static byte[] encrypt(byte[] data, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }

    /**
     * 私钥解密
     *
     * @author guoqun.yang
     * @date   2018/5/18 23:05
     * @param   data
     * @param   privateKey
     * @return byte[]
     * @version 1.0
     */
    public static byte[] decrypt(byte[] data, RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }
}

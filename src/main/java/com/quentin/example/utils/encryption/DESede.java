package com.quentin.example.utils.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * DESede加解密
 *
 * @Author Created by guoqun.yang
 * @Date Created in 14:24 2017/12/27
 * @Version 1.0
 */
public class DESede {

	/**
	 * 生成密钥
	 *
	 * @author guoqun.yang
	 * @date   2018/5/18 23:09
	 * @param
	 * @return byte[]
	 * @version 1.0
	 */
	public static byte[] initKey() throws Exception{
		//密钥生成器
		KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
		//初始化密钥生成器
		//可指定密钥长度为112或168，默认168
		keyGen.init(168);
		//生成密钥
		SecretKey secretKey = keyGen.generateKey();
		return secretKey.getEncoded();
	}
	
	/**
	 * 加密
	 *
	 * @author guoqun.yang
	 * @date   2018/5/18 23:08
	 * @param   data
	 * @param   key
	 * @return byte[]
	 * @version 1.0
	 */
	public static byte[] encrypt3DES(byte[] data, byte[] key) throws Exception{
		//恢复密钥
		SecretKey secretKey = new SecretKeySpec(key, "DESede");
		//Cipher完成加密
		Cipher cipher = Cipher.getInstance("DESede");
		//cipher初始化
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encrypt = cipher.doFinal(data);
		
		return encrypt;
	}
	
	/**
	 * 解密
	 *
	 * @author guoqun.yang
	 * @date   2018/5/18 23:08
	 * @param   data
	 * @param   key
	 * @return byte[]
	 * @version 1.0
	 */
	public static byte[] decrypt3DES(byte[] data, byte[] key) throws Exception{
		//恢复密钥
		SecretKey secretKey = new SecretKeySpec(key, "DESede");
		//Cipher完成解密
		Cipher cipher = Cipher.getInstance("DESede");
		//初始化cipher
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] plain = cipher.doFinal(data);
		
		return plain;
	}
}

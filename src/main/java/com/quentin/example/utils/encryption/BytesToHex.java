package com.quentin.example.utils.encryption;

/**
 * 字节码转化十六进制
 *
 * @Author Created by guoqun.yang
 * @Date Created in 14:24 2017/12/27
 * @Version 1.0
 */
public class BytesToHex {

	public static String fromBytesToHex(byte[] resultBytes){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < resultBytes.length; i++){
			if(Integer.toHexString(0xFF & resultBytes[i]).length() == 1){
				builder.append("0").append(Integer.toHexString(0xFF & resultBytes[i]));
			}else{
				builder.append(Integer.toHexString(0xFF & resultBytes[i]));
			}
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.toHexString(0xFF & 15));
	}
}

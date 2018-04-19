package com.quentin.example.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class BASE64Utils
{
  public static byte[] decryptBASE64(String key)
    throws IOException
  {
    return new BASE64Decoder().decodeBuffer(key);
  }
  
  public static String encryptBASE64(byte[] key)
  {
    return new BASE64Encoder().encodeBuffer(key);
  }
}

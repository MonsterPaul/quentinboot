package com.quentin.example.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class SHAUtils
{
  private static final SecureRandom random = new SecureRandom();
  private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  
  public static String md5(String srcStr)
  {
    return hash("MD5", srcStr);
  }
  
  public static String sha1(String srcStr)
  {
    return hash("SHA-1", srcStr);
  }
  
  public static String sha256(String srcStr)
  {
    return hash("SHA-256", srcStr);
  }
  
  public static String sha384(String srcStr)
  {
    return hash("SHA-384", srcStr);
  }
  
  public static String sha512(String srcStr)
  {
    return hash("SHA-512", srcStr);
  }
  
  public static String hash(String algorithm, String srcStr)
  {
    try
    {
      MessageDigest md = MessageDigest.getInstance(algorithm);
      byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
      return toHex(bytes);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private static String toHex(byte[] bytes)
  {
    StringBuilder stringBuilder = new StringBuilder(bytes.length * 2);
    for (int i = 0; i < bytes.length; i++)
    {
      stringBuilder.append(HEX_DIGITS[(bytes[i] >> 4 & 0xF)]);
      stringBuilder.append(HEX_DIGITS[(bytes[i] & 0xF)]);
    }
    return stringBuilder.toString();
  }
  
  public static String generateSalt(int numberOfBytes)
  {
    byte[] salt = new byte[numberOfBytes];
    random.nextBytes(salt);
    return toHex(salt);
  }
}


```java
package com.goods.springbootmybatisgoods.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class MD5Util {
    /**
     * 生成盐值
     * @param source 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String md5(String source) {
        try {
            // 创建MD5消息摘要对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入字符串转换为字节数组
            byte[] inputBytes = source.getBytes();

            // 计算MD5摘要
            byte[] md5Bytes = md.digest(inputBytes);

            // 将字节数组转换为16进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : md5Bytes) {
                sb.append(String.format("%02x", b));
            }

            String md5Hash = sb.toString();

            System.out.println("MD5 Hash: " + md5Hash);
            return md5Hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成盐值
     * @param source 需要加密的字符串
     * @param salt 盐值
     * @return 加密后的字符串
     */
    public static String md5(String source, String salt) {
        try {
            byte[] saltByte = salt.getBytes();
            // 创建MD5消息摘要对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将密码和盐值连接
            byte[] passwordWithSalt = concatenateByteArrays(source.getBytes(), saltByte);

            // 计算MD5摘要
            byte[] md5Bytes = md.digest(passwordWithSalt);

            // 将字节数组转换为16进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : md5Bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return source;
    }

    private static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    /**
     * 加密密码 两次MD5
     * @param password 密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    public static String encodePassword(String password, String salt) {
        return md5(md5(password, salt));
    }
}
```

/** 
 * 文件名：AESCodec.java
 * 创建时间：2017年5月4日 下午5:07:25
 * 描述：用一句话描述该文件
 * 版权所有：浙江乐信付金融服务有限公司
 * @author abacusli
 */
package com.lechinepay.framework.security.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @ClassName AESCodec
 * @Description 用一句话描述该类的作用
 * @author abacusli
 * @date 2017年5月4日 下午5:07:25
 */
public class AESCodec {

    // 密钥算法
    public static final String KEY_ALGORITHM    = "AES";

    // 加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static final String SECRET_KEY       = "7D1aBvl/dUR8djbvjw7WJA==";

    /**
     * 生成密钥
     */
    public static String initkey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM); // 实例化密钥生成器
        kg.init(128); // 初始化密钥生成器:AES要求密钥长度为128,192,256位
        SecretKey secretKey = kg.generateKey(); // 生成密钥
        return Base64.encodeBase64String(secretKey.getEncoded()); // 获取二进制密钥编码形式
    }

    /**
     * 转换密钥
     */
    public static Key toKey(byte[] key) throws Exception {
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    /**
     * 加密数据
     * 
     * @param data
     *            待加密数据
     * @param key
     *            密钥
     * @return 加密后的数据
     */
    public static String encrypt(String data, String key) throws Exception {
        Key k = toKey(Base64.decodeBase64(key)); // 还原密钥
        // 使用PKCS7Padding填充方式,这里就得这么写了(即调用BouncyCastle组件实现)
        // Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM); // 实例化Cipher对象，它用于完成实际的加密操作
        cipher.init(Cipher.ENCRYPT_MODE, k); // 初始化Cipher对象，设置为加密模式
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes())); // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
    }

    /**
     * 解密数据
     * 
     * @param data
     *            待解密数据
     * @param key
     *            密钥
     * @return 解密后的数据
     */
    public static String decrypt(String data, String key) throws Exception {
        Key k = toKey(Base64.decodeBase64(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k); // 初始化Cipher对象，设置为解密模式
        return new String(cipher.doFinal(Base64.decodeBase64(data))); // 执行解密操作
    }

    public static void main(String[] args) throws Exception {
        String source = "明文数据";
        String key;
        if (args.length == 0) {
            key = SECRET_KEY;
        } else if (args.length == 1) {
            source = args[0];
            key = initkey();
        } else if (args.length == 2) {
            source = args[0];
            key = args[1];
        } else {
            throw new RuntimeException("args.length is 1 or 2");
        }
        System.out.println("key:" + key);
        System.out.println("原文：" + source);
        System.out.println("原文长度：" + source.length());

        String encryptData = encrypt(source, key);
        System.out.println("加密：" + encryptData);
        System.out.println("加密后长度：" + encryptData.length());

        String decryptData = decrypt(encryptData, key);
        System.out.println("解密: " + decryptData);

    }
}

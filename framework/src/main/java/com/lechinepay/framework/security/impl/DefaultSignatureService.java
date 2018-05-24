package com.lechinepay.framework.security.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lechinepay.framework.security.CertificateService;
import com.lechinepay.framework.security.KeyStoreService;
import com.lechinepay.framework.security.SignatureService;

public class DefaultSignatureService implements SignatureService {

    /**
     * 算法常量： SHA1
     */
    private static final String ALGORITHM_SHA1 = "SHA-1";

    public static final String  SIGN_ALGORITHM = "SHA1WithRSA";

    private static final Logger LOGGER         = LoggerFactory.getLogger(DefaultSignatureService.class);

    private String              algorithm;

    private CertificateService  certificateService;

    private KeyStoreService     keyStoreService;

    public String getAlgorithm() {
        return null == algorithm ? SIGN_ALGORITHM : algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = null == algorithm ? SIGN_ALGORITHM : algorithm;
    }

    public CertificateService getCertificateService() {
        return certificateService;
    }

    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    public KeyStoreService getKeyStoreService() {
        return keyStoreService;
    }

    public void setKeyStoreService(KeyStoreService keyStoreService) {
        this.keyStoreService = keyStoreService;
    }

    @Override
    public byte[] sign(byte[] data) throws SecurityException {
        try {
            Signature st = Signature.getInstance(getAlgorithm());
            PrivateKey privateKey = getKeyStoreService().getPrivateKey();
            st.initSign(privateKey);
            st.update(data);
            return st.sign();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (SignatureException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        }
    }

    @Override
    public boolean verify(byte[] data, byte[] sign) {
        try {
            Signature st = Signature.getInstance(getAlgorithm());
            PublicKey publicKey = getCertificateService().getPublicKey();
            st.initVerify(publicKey);
            st.update(data);
            return st.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (SignatureException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public String sign(String data) throws SecurityException {
        byte[] dataForSign = sha1X16(data, Charset.defaultCharset().name());
        return new String(Base64.encode(sign(dataForSign)));
    }

    @Override
    public boolean verify(String data, String sign) {
        byte[] dataForVerify = sha1X16(data, Charset.defaultCharset().name());
        return verify(dataForVerify, Base64.decode(sign.getBytes()));
    }

    @Override
    public String sign(String data, String encoding) throws SecurityException {
        byte[] dataForSign = sha1X16(data, encoding);
        return new String(Base64.encode(sign(dataForSign)));
    }

    @Override
    public boolean verify(String data, String sign, String encoding) {
        byte[] dataForVerify = sha1X16(data, encoding);
        return verify(dataForVerify, Base64.decode(sign.getBytes()));
    }

    /**
     * sha1计算.
     * 
     * @param datas
     *            待计算的数据
     * @return 计算结果
     */
    protected byte[] sha1(byte[] data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(ALGORITHM_SHA1);
            md.reset();
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        }
    }

    /**
     * sha1计算
     * 
     * @param datas
     *            待计算的数据
     * @param encoding
     *            字符集编码
     * @return
     */
    protected byte[] sha1(String datas, String encoding) {
        try {
            return sha1(datas.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        }
    }

    /**
     * sha1计算后进行16进制转换
     * 
     * @param data
     *            待计算的数据
     * @param encoding
     *            编码
     * @return 计算结果
     */
    protected byte[] sha1X16(String data, String encoding) {
        byte[] bytes = sha1(data, encoding);
        StringBuilder sha1StrBuff = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (Integer.toHexString(0xFF & bytes[i]).length() == 1) {
                sha1StrBuff.append("0").append(Integer.toHexString(0xFF & bytes[i]));
            } else {
                sha1StrBuff.append(Integer.toHexString(0xFF & bytes[i]));
            }
        }
        try {
            return sha1StrBuff.toString().getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        }
    }
}

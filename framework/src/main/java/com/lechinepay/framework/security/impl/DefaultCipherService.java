package com.lechinepay.framework.security.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lechinepay.framework.security.CertificateService;
import com.lechinepay.framework.security.CipherService;
import com.lechinepay.framework.security.KeyStoreService;

public class DefaultCipherService implements CipherService {

    public static final String  CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    private static final Logger LOGGER                = LoggerFactory.getLogger(DefaultCipherService.class);

    private String              transformation;

    private CertificateService  certificateService;

    private KeyStoreService     keyStoreService;

    public String getTransformation() {
        return null == transformation ? CIPHER_TRANSFORMATION : transformation;
    }

    public void setTransformation(String transformation) {
        this.transformation = null == transformation ? CIPHER_TRANSFORMATION : transformation;
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
    public byte[] encrypt(byte[] data) throws SecurityException {
        try {
            Key publicKey = getCertificateService().getPublicKey();
            return update(data, Cipher.ENCRYPT_MODE, publicKey);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (BadPaddingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (ShortBufferException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data) throws SecurityException {
        try {
            Key privateKey = getKeyStoreService().getPrivateKey();
            return update(data, Cipher.DECRYPT_MODE, privateKey);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (BadPaddingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        } catch (ShortBufferException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        }
    }

    @Override
    public String encrypt(String data) throws SecurityException {
        return new String(Base64.encode(encrypt(data.getBytes())));

    }

    @Override
    public String decrypt(String data) throws SecurityException {
        return new String(decrypt(Base64.decode(data.getBytes())));
    }

    @Override
    public String encrypt(String data, String encoding) throws SecurityException {
        try {
            return new String(Base64.encode(encrypt(data.getBytes(encoding))));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        }
    }

    @Override
    public String decrypt(String data, String encoding) throws SecurityException {
        try {
            return new String(decrypt(Base64.decode(data.getBytes())), encoding);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SecurityException(e.getMessage(), e);
        }
    }

    protected byte[] update(byte[] data, int mode, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(getTransformation(),
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher.init(mode, key);
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(data.length);
        int leavedSize = data.length % blockSize;
        int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
        byte[] result = new byte[outputSize * blocksSize];
        int i = 0;
        while (data.length - i * blockSize > 0) {
            if (data.length - i * blockSize > blockSize) {
                cipher.doFinal(data, i * blockSize, blockSize, result, i * outputSize);
            } else {
                cipher.doFinal(data, i * blockSize, data.length - i * blockSize, result, i * outputSize);
            }
            i++;
        }
        return result;
    }

}

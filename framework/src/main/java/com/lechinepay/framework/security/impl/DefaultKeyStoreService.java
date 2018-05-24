package com.lechinepay.framework.security.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lechinepay.framework.security.KeyStoreService;
import com.lechinepay.framework.util.io.Closeables;

public class DefaultKeyStoreService implements KeyStoreService {

    public static final String  KEY_STORE_TYPE = "JKS";

    private static final Logger LOGGER         = LoggerFactory.getLogger(DefaultKeyStoreService.class);

    private String              keyStorePath;

    private String              keyStoreType;

    private String              keyStorePassword;

    private KeyStore            keyStore;

    public String getKeyStorePath() {
        return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    public String getKeyStoreType() {
        return null == keyStoreType ? KEY_STORE_TYPE : keyStoreType;
    }

    public void setKeyStoreType(String keyStoreType) {
        this.keyStoreType = null == keyStoreType ? KEY_STORE_TYPE : keyStoreType;
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    @Override
    public KeyStore getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public void init() {
        if (null != getKeyStore()) {
            return;
        }
        InputStream inputStream = null;
        try {
            keyStore = KeyStore.getInstance(getKeyStoreType());
            inputStream = DefaultKeyStoreService.class.getClassLoader().getResourceAsStream(keyStorePath);
            keyStore.load(inputStream, keyStorePassword.toCharArray());
        } catch (KeyStoreException e) {
            keyStore = null;
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            keyStore = null;
            LOGGER.error(e.getMessage(), e);
        } catch (CertificateException e) {
            keyStore = null;
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            keyStore = null;
            LOGGER.error(e.getMessage(), e);
        } finally {
            Closeables.closeQuietly(inputStream);
        }
    }

    @Override
    public PrivateKey getPrivateKey() {
        PrivateKey privateKey = null;
        Enumeration<String> aliasenum;
        try {
            aliasenum = keyStore.aliases();
            String alias = null;
            if (aliasenum.hasMoreElements()) {
                alias = aliasenum.nextElement();
            }
            if (null != alias) {
                privateKey = getPrivateKey0(alias);
            }
        } catch (KeyStoreException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return privateKey;
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        if (null == alias) {
            return null;
        }
        PrivateKey privateKey = null;
        Enumeration<String> aliasenum;
        try {
            aliasenum = keyStore.aliases();
            while (aliasenum.hasMoreElements() && alias.equals(aliasenum.nextElement())) {
                privateKey = getPrivateKey0(alias);
                return privateKey;
            }
        } catch (KeyStoreException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return privateKey;
    }

    private PrivateKey getPrivateKey0(String alias) {
        try {
            return (PrivateKey) keyStore.getKey(alias, keyStorePassword.toCharArray());
        } catch (UnrecoverableKeyException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (KeyStoreException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

}

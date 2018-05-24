package com.lechinepay.framework.security;

import java.security.KeyStore;
import java.security.PrivateKey;

public interface KeyStoreService {

    KeyStore getKeyStore();

    PrivateKey getPrivateKey();

    PrivateKey getPrivateKey(String alias);

}

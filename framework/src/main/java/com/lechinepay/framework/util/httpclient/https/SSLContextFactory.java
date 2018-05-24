package com.lechinepay.framework.util.httpclient.https;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class SSLContextFactory {

    public static SSLContext createSSLContext() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[] { DefaultX509TrustManager.DEFAULT_X509_TRUST_MANAGER }, null);
            return sslContext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }
}

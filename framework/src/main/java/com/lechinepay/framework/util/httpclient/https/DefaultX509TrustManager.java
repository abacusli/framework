package com.lechinepay.framework.util.httpclient.https;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class DefaultX509TrustManager implements X509TrustManager {

    public static final X509TrustManager DEFAULT_X509_TRUST_MANAGER = new DefaultX509TrustManager();

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}

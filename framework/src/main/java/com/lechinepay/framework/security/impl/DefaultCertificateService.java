package com.lechinepay.framework.security.impl;

import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lechinepay.framework.security.CertificateService;
import com.lechinepay.framework.util.io.Closeables;

public class DefaultCertificateService implements CertificateService {

    public static final String  CERTIFICATE_TYPE = "X.509";

    private static final Logger LOGGER           = LoggerFactory.getLogger(DefaultCertificateService.class);

    private String              certificatePath;

    private String              certificateType;

    private Certificate         certificate;

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public String getCertificateType() {
        return null == certificateType ? CERTIFICATE_TYPE : certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = null == certificateType ? CERTIFICATE_TYPE : certificateType;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public Certificate getCertificate() {
        return certificate;
    }

    public void init() {
        if (null != getCertificate()) {
            return;
        }
        InputStream inputStream = null;
        CertificateFactory certificateFactory = null;
        try {
            certificateFactory = CertificateFactory.getInstance(getCertificateType());
            inputStream = DefaultCertificateService.class.getClassLoader().getResourceAsStream(getCertificatePath());
            certificate = certificateFactory.generateCertificate(inputStream);
        } catch (CertificateException e) {
            certificate = null;
            LOGGER.error(e.getMessage(), e);
        } finally {
            Closeables.closeQuietly(inputStream);
        }
    }

    @Override
    public PublicKey getPublicKey() {
        return getCertificate().getPublicKey();
    }
}

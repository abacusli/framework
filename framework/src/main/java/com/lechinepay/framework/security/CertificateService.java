package com.lechinepay.framework.security;

import java.security.PublicKey;
import java.security.cert.Certificate;

public interface CertificateService {

    Certificate getCertificate();

    PublicKey getPublicKey();
}

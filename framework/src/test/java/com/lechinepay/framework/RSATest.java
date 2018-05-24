package com.lechinepay.framework;

import com.lechinepay.framework.security.impl.DefaultCertificateService;
import com.lechinepay.framework.security.impl.DefaultCipherService;
import com.lechinepay.framework.security.impl.DefaultKeyStoreService;
import com.lechinepay.framework.security.impl.DefaultSignatureService;
import com.lechinepay.framework.security.impl.PemCertificateService;

public class RSATest {

    public static void main(String[] args) {
        DefaultKeyStoreService keyStoreService = new DefaultKeyStoreService();
        String keyStorePassword = "123456";
        keyStoreService.setKeyStorePassword(keyStorePassword);
        String keyStorePath = "test01.keystore";
        keyStoreService.setKeyStorePath(keyStorePath);
        keyStoreService.init();
        DefaultCertificateService certificateService = new PemCertificateService();
        // certificateService.setCertificatePath("test01.crt");
        certificateService.setCertificatePath("aps_production_identity01.pem");
        certificateService.init();

        DefaultCipherService defaultCipherService = new DefaultCipherService();
        defaultCipherService.setCertificateService(certificateService);
        defaultCipherService.setKeyStoreService(keyStoreService);
        DefaultSignatureService defaultSignatureService = new DefaultSignatureService();
        defaultSignatureService.setCertificateService(certificateService);
        defaultSignatureService.setKeyStoreService(keyStoreService);
        String data = "123456789理朝阳";
        System.out.println(defaultSignatureService.verify(data, defaultSignatureService.sign(data)));

        String s = defaultCipherService.encrypt(data, "utf-8");
        System.out.println(new String(s));
        s = defaultCipherService.decrypt(s, "utf-8");

        System.out.println(s);

    }
}

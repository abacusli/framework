package com.lechinepay.framework;

import com.lechinepay.framework.security.impl.DefaultCertificateService;
import com.lechinepay.framework.security.impl.DefaultCipherService;
import com.lechinepay.framework.security.impl.DefaultKeyStoreService;
import com.lechinepay.framework.security.impl.DefaultSignatureService;
import com.lechinepay.framework.security.impl.PemCertificateService;

public class RSATest_pkcs12 {

    public static void main(String[] args) {
        DefaultKeyStoreService keyStoreService = new DefaultKeyStoreService();
        String keyStorePassword = "123456";
        keyStoreService.setKeyStorePassword(keyStorePassword);
        String keyStorePath = "testclient.pfx";
        keyStoreService.setKeyStorePath(keyStorePath);
        keyStoreService.init();
        DefaultCertificateService certificateService = new PemCertificateService();
        // certificateService.setCertificatePath("testclient.cer");
        certificateService.setCertificatePath("aps_production_identity.pem");
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
        s="QXDh66rNZBJAOaKQaZevxEQkuZAm0tOUNqKevz/UlcIoEqsHPkbX2o2lzJP8d4CpHQvrrF+rm/EBozS8xXbpOsTkYt9NU2al04CkD7YPQXnerFteJhjuPazHhiEZc2oBGVA62CmSNdRtQZU+5mkdHhECwTKYsxS6SNB+bDQazdo=";
        s = defaultCipherService.decrypt(s, "utf-8");

        System.out.println(s);

    }
}

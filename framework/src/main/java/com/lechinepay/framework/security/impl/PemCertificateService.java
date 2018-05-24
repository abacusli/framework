package com.lechinepay.framework.security.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lechinepay.framework.util.io.Closeables;

public class PemCertificateService extends DefaultCertificateService {

    private static final Logger LOGGER        = LoggerFactory.getLogger(PemCertificateService.class);

    public static final String  PEM_EXTENSION = ".pem";

    @Override
    public void init() {
        if (null != getCertificate()) {
            return;
        }
        String path = getCertificatePath();
        if (path.endsWith(PEM_EXTENSION)) {
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            ByteArrayInputStream arrayInputStream = null;
            CertificateFactory certificateFactory = null;
            try {
                certificateFactory = CertificateFactory.getInstance(getCertificateType());
                inputStream = DefaultCertificateService.class.getClassLoader()
                        .getResourceAsStream(getCertificatePath());
                inputStreamReader = new InputStreamReader(inputStream);
                LineIterator lineIterator = IOUtils.lineIterator(inputStreamReader);
                List<String> list = new ArrayList<String>();
                while (lineIterator.hasNext()) {
                    list.add(lineIterator.nextLine());
                }
                list.remove(0);
                list.remove(list.size() - 1);
                StringBuilder sb = new StringBuilder();
                for (String s : list) {
                    sb.append(s);
                }
                byte[] encodedKey = sb.toString().getBytes();
                encodedKey = Base64.decode(encodedKey);
                arrayInputStream = new ByteArrayInputStream(encodedKey);
                Certificate certificate = certificateFactory.generateCertificate(arrayInputStream);
                setCertificate(certificate);
            } catch (CertificateException e) {
                setCertificate(null);
                LOGGER.error(e.getMessage(), e);
            } finally {
                Closeables.closeQuietly(arrayInputStream);
                Closeables.closeQuietly(inputStreamReader);
                Closeables.closeQuietly(inputStream);
            }
        } else {
            super.init();
        }
    }
}

package com.xapps.utility.xsigner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.ByteArrayInputStream;

public class TestkeyDate {

    public static String getTestkeyExpiry(String pemPath) {
        try {
            String pemFilePath = pemPath;
            String pemContent = readPemFile(pemFilePath);
            byte[] derBytes = decodePem(pemContent);
            X509Certificate certificate = parseCertificate(derBytes);
            Date expirationDate = certificate.getNotAfter();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            return sdf.format(expirationDate);
        } catch (Exception e) {
            return e.toString();
        }
    }

    private static String readPemFile(String pemFilePath) throws IOException {
        StringBuilder pemContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(pemFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("-----BEGIN CERTIFICATE-----") &&
                    !line.startsWith("-----END CERTIFICATE-----")) {
                    pemContent.append(line);
                }
            }
        }
        return pemContent.toString();
    }

    private static byte[] decodePem(String pemContent) {
        return Base64.getDecoder().decode(pemContent);
    }

    private static X509Certificate parseCertificate(byte[] derBytes) throws CertificateException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        return (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(derBytes));
    }
}
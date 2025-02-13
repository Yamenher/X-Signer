package com.xapps.utility.xsigner;

import com.android.apksig.ApkSigner;
import com.android.apksig.ApkSigner.SignerConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.io.ByteArrayInputStream;

public class TestKeySigner {

    // Utility method to read all bytes from a FileInputStream (compatible with API 26)
    private static byte[] readAllBytes(FileInputStream inputStream) throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384]; // Buffer size

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        return buffer.toByteArray();
    }

    private static List<X509Certificate> loadCertificates(File pemFile) throws Exception {
        List<X509Certificate> certificates = new ArrayList<>();
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

        try (BufferedReader reader = new BufferedReader(new FileReader(pemFile))) {
            StringBuilder pemContent = new StringBuilder();
            String line;
            boolean inCertificate = false;
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("-----BEGIN CERTIFICATE-----")) {
                    inCertificate = true;
                } else if (line.startsWith("-----END CERTIFICATE-----")) {
                    inCertificate = false;
                    String certString = pemContent.toString();
                    byte[] certBytes = Base64.getDecoder().decode(certString);
                    X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(certBytes));
                    certificates.add(certificate);
                    pemContent.setLength(0); // Reset the buffer
                } else if (inCertificate) {
                    pemContent.append(line.trim());
                }
            }
        }
        return certificates;
    }

    public static void signApk(File apkFile, File pemFile, File pk8File, String outputApkFile,
                               boolean useV1, boolean useV2, boolean useV3, boolean useV4, boolean zipalign) throws Exception {

        List<X509Certificate> certificates = loadCertificates(pemFile);

        // Load PK8 private key
        PrivateKey privateKey;
        try (FileInputStream pk8InputStream = new FileInputStream(pk8File)) {
            byte[] pk8Bytes = readAllBytes(pk8InputStream); // Use the custom method
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pk8Bytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        }

        SignerConfig.Builder signerBuilder = new ApkSigner.SignerConfig.Builder("testkey", privateKey, certificates);
        SignerConfig signerConfig = signerBuilder.build();

        ApkSigner.Builder apkSignerBuilder = new ApkSigner.Builder(Collections.singletonList(signerConfig));
                apkSignerBuilder.setInputApk(apkFile);
                apkSignerBuilder.setOutputApk(new File (outputApkFile));
                apkSignerBuilder.setV1SigningEnabled(useV1);
                apkSignerBuilder.setV2SigningEnabled(useV2);
                apkSignerBuilder.setV3SigningEnabled(useV3);
                if (useV4) {
		         	apkSignerBuilder.setV4SignatureOutputFile(new File(outputApkFile.replace("apk", "idsig")));
		         	apkSignerBuilder.setV4SigningEnabled(true);
                 }
		     	apkSignerBuilder.setAlignFileSize(zipalign);

        ApkSigner apkSigner = apkSignerBuilder.build();
        apkSigner.sign();
    }

    public static void signWithTestkey(String input, String output, String pem, String pk8, boolean v1, boolean v2, boolean v3, boolean v4, boolean zipalign) {
        try {
            // Replace with actual paths
            File apkFile = new File(input);
            File pemFile = new File(pem);
            File pk8File = new File(pk8);

            signApk(apkFile, pemFile, pk8File, output, v1, v2, v3, v4, zipalign);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
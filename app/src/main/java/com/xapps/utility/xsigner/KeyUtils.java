package com.xapps.utility.xsigner;

import android.sun.security.provider.JavaKeyStoreProvider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

public class KeyUtils {

    static {
        // Add Bouncy Castle provider if not already added
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        Security.addProvider(new JavaKeyStoreProvider());
    }

    public static void createKeyStore(String type, String filename, String keystorePassword, String alias, String aliasPassword, String dn, int validityYears, int keySize) throws Exception {
        KeyStore keyStore;

        if ("JKS".equalsIgnoreCase(type)) {
            // Use Android-specific JavaKeyStoreProvider for JKS
            keyStore = KeyStore.getInstance("JKS");
        } else {
            // Use Bouncy Castle provider for BKS and PKCS12
            keyStore = KeyStore.getInstance(type, "BC");
        }

        keyStore.load(null, keystorePassword.toCharArray());

        // Generate key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Generate self-signed certificate
        Certificate[] certificateChain = {generateSelfSignedCertificate(keyPair, dn, validityYears)};

        // Store the key pair in the KeyStore with alias password
        keyStore.setKeyEntry(alias, keyPair.getPrivate(), aliasPassword.toCharArray(), certificateChain);

        // Save the KeyStore to a file
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            keyStore.store(fos, keystorePassword.toCharArray());
        }

        System.out.println(type + " KeyStore created successfully: " + filename);
    }

    private static X509Certificate generateSelfSignedCertificate(KeyPair keyPair, String dn, int validityYears) throws Exception {
        long now = System.currentTimeMillis();
        Date startDate = new Date(now);
        Date endDate = new Date(now + (long) validityYears * 365 * 24 * 60 * 60 * 1000L);

        X500Principal subject = new X500Principal(dn);
        JcaX509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
                subject,
                BigInteger.valueOf(now),
                startDate,
                endDate,
                subject,
                keyPair.getPublic()
        );

        ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256WithRSAEncryption").build(keyPair.getPrivate());
        return new JcaX509CertificateConverter().getCertificate(certBuilder.build(contentSigner));
    }
}
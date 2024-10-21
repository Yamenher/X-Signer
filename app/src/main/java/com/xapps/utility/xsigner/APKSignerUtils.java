package com.xapps.utility.xsigner;

import com.android.apksig.ApkSigner;
import com.android.apksig.ApkSigner.SignerConfig;
import com.android.apksig.util.DataSources;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import android.sun.security.provider.JavaKeyStoreProvider;
import java.security.Security;
import java.io.PrintWriter;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class APKSignerUtils {
    private static KeyStore keyStore;

    public static void signFile(String inputFile, String outputFile, String keyFile, String keyAlias, String keystorePassword, String keyPassword, boolean v1, boolean v2, boolean v3, boolean v4, boolean zipalign, String type) {
        File errorLogFile = new File("/data/data/com.xapps.utility.xsigner/error.txt");

        try {
            if (type.equals("JKS")) {
                keyStore = KeyStore.getInstance("JKS", new JavaKeyStoreProvider());
            } else {
                keyStore = KeyStore.getInstance(type, new BouncyCastleProvider());
            }

            try (FileInputStream fis = new FileInputStream(new File(keyFile))) {
                keyStore.load(fis, keystorePassword.toCharArray());
            }

            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, keyPassword.toCharArray());
            Certificate[] certChain = keyStore.getCertificateChain(keyAlias);

            if (certChain == null || certChain.length == 0) {
                throw new Exception("Certificate chain is empty or null.");
            }

            List<X509Certificate> certificateList = new ArrayList<>();
            for (Certificate cert : certChain) {
                if (cert instanceof X509Certificate) {
                    certificateList.add((X509Certificate) cert);
                }
            }

            if (certificateList.isEmpty()) {
                throw new Exception("No valid X509Certificate found in certificate chain.");
            }
            
            SignerConfig signerConfig = new SignerConfig.Builder(keyAlias, privateKey, certificateList, true).build();
            List<SignerConfig> signerConfigs = Collections.singletonList(signerConfig);

            File outputApkFile = new File(outputFile);
            System.out.println("Output APK File Path: " + outputApkFile.getAbsolutePath());

            try (FileChannel inputChannel = new FileInputStream(new File(inputFile)).getChannel()) {
                ApkSigner.Builder builder = new ApkSigner.Builder(signerConfigs);
                builder.setInputApk(DataSources.asDataSource(inputChannel));
                builder.setOutputApk(outputApkFile);
                builder.setV1SigningEnabled(v1);
                builder.setV2SigningEnabled(v2);
                builder.setV3SigningEnabled(v3);

                if (v4) {
                    builder.setV4SignatureOutputFile(new File(outputFile + ".idsig"));
                    builder.setV4SigningEnabled(true);
                }
                Log.i("info", "signing is starting...");
                builder.setAlignFileSize(zipalign);
                ApkSigner apkSigner = builder.build();
                apkSigner.sign();
                
            }

        }catch (FileNotFoundException e1) {
            
            return;
        
        }catch (Exception e) {
            try (FileOutputStream fos = new FileOutputStream(errorLogFile, true);
                 PrintWriter writer = new PrintWriter(fos)) {
                writer.println("Exception: " + e.toString());
                e.printStackTrace(writer);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } 
    }
}
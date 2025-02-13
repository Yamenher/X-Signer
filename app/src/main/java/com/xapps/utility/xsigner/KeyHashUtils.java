package com.xapps.utility.xsigner;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.security.KeyStore;
import java.security.cert.Certificate;
import android.sun.security.provider.JavaKeyStoreProvider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import android.net.Uri;

public class KeyHashUtils {
	private static KeyStore keyStore;
	
	private static X509Certificate certificate;
	private static byte[] certificateBytes;
	
	public static X509Certificate loadCertificate(File path) throws Exception {
		FileInputStream fis = new FileInputStream(path);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate) certFactory.generateCertificate(bis);
		bis.close();
		
		return cert;
	}
	
	public static byte[] generateDigest(byte[] data, String algorithm) throws Exception {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		return digest.digest(data);
	}
	
	
	public static String getTestkeyDigest(File path, String type) throws Exception {
		certificate = loadCertificate(path);
		certificateBytes = certificate.getEncoded();
		byte[] sha256Digest = generateDigest(certificateBytes, type);
		String sha256 = HexEncoding.encode(sha256Digest);
		return sha256;
	}
	
	public static String getKeyDigest(String keystoreStream, String keystorePassword, String alias, String type) throws Exception {
		if (Uri.parse(keystoreStream.toLowerCase()).getLastPathSegment().substring((int)(Uri.parse(keystoreStream.toLowerCase()).getLastPathSegment().length() - ".".concat("jks").length()), (int)(Uri.parse(keystoreStream.toLowerCase()).getLastPathSegment().length())).equals(".".concat("jks"))) {
			keyStore = KeyStore.getInstance("JKS", new JavaKeyStoreProvider());
		} else if (Uri.parse(keystoreStream.toLowerCase()).getLastPathSegment().substring((int)(Uri.parse(keystoreStream.toLowerCase()).getLastPathSegment().length() - ".".concat("pkcs12").length()), (int)(Uri.parse(keystoreStream.toLowerCase()).getLastPathSegment().length())).equals(".".concat("pkcs12"))) {
			keyStore = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
		} else {
			keyStore =  KeyStore.getInstance("BKS", new BouncyCastleProvider());
		}
		try (FileInputStream fis = new FileInputStream(keystoreStream)) {
			keyStore.load(fis, keystorePassword.toCharArray());
		}
		Certificate certificate = keyStore.getCertificate(alias);
		if (certificate == null) {
			throw new RuntimeException("Certificate with alias " + alias + " not found");
		}
		MessageDigest messageDigest = MessageDigest.getInstance(type);
		byte[] digest = messageDigest.digest(certificate.getEncoded());
		return HexEncoding.encode(digest);
		
	}
}

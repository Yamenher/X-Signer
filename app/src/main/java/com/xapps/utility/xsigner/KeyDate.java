package com.xapps.utility.xsigner;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.sun.security.provider.JavaKeyStoreProvider;
import java.util.Enumeration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import android.net.Uri;

public class KeyDate {
	private static KeyStore keyStore;
	private static String dataResult;
	
	public static String getKeyData(String keypath, String keystorepass, String type) throws Exception {
		
		String keyStorePath = keypath;
		char[] keyStorePassword = keystorepass.toCharArray();
		
		KeyStore keyStore = loadKeyStore(keyStorePath, keyStorePassword);
		Enumeration<String> aliases = keyStore.aliases();
		final String alias = aliases.nextElement();
		Certificate certificate = keyStore.getCertificate(alias);
		if (certificate instanceof X509Certificate) {
			X509Certificate x509Certificate = (X509Certificate) certificate;
			final Date expirationDate = x509Certificate.getNotAfter();
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			if (type == "exp") {
				dataResult = sdf.format(expirationDate);
			} else if (type == "alias") {
				dataResult = alias;
			}
		}
		return dataResult;
		
	}
	
	
	private static KeyStore loadKeyStore(String keyStorePath, char[] password) throws Exception {
		if (Uri.parse(keyStorePath.toLowerCase()).getLastPathSegment().substring((int)(Uri.parse(keyStorePath.toLowerCase()).getLastPathSegment().length() - ".".concat("jks").length()), (int)(Uri.parse(keyStorePath.toLowerCase()).getLastPathSegment().length())).equals(".".concat("jks"))) {
			keyStore = KeyStore.getInstance("JKS", new JavaKeyStoreProvider());
		} else if (Uri.parse(keyStorePath.toLowerCase()).getLastPathSegment().substring((int)(Uri.parse(keyStorePath.toLowerCase()).getLastPathSegment().length() - ".".concat("pkcs12").length()), (int)(Uri.parse(keyStorePath.toLowerCase()).getLastPathSegment().length())).equals(".".concat("pkcs12"))) {
			keyStore = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
		} else {
			keyStore =  KeyStore.getInstance("BKS", new BouncyCastleProvider());
		}
		try (FileInputStream fis = new FileInputStream(keyStorePath)) {
			keyStore.load(fis, password);
		}
		return keyStore;
	}
}

package com.xapps.utility.xsigner;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SignatureVerifier {
    
    private static boolean isCorrupt;

    public static boolean checkSignatureFilesExist(String apkFile) {
        boolean hasMF = false;
        boolean hasRSA = false;
        boolean hasSF = false;

        try (ZipFile zipFile = new ZipFile(new File(apkFile))) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryName = entry.getName();

                if (entryName.startsWith("META-INF/") && entryName.endsWith(".MF")) {
                    hasMF = true;
                } else if (entryName.startsWith("META-INF/") && entryName.endsWith(".RSA")) {
                    hasRSA = true;
                } else if (entryName.startsWith("META-INF/") && entryName.endsWith(".SF")) {
                    hasSF = true;
                }

                // If all files are found, exit the loop early
                if (hasMF && hasRSA && hasSF) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isCorrupt = true;
            return false;
        }

        return hasMF && hasRSA && hasSF;
    }
    
    public static boolean isValidZipFile() {
        return (!isCorrupt);
    }   
}
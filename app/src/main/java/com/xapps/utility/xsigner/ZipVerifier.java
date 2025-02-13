package com.xapps.utility.xsigner;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipVerifier {
    
    private static boolean isAab;
    private static boolean isApk;
    private static boolean hasMF;
    private static boolean hasArsc;
    private static boolean hasBundle;
    private static boolean hasRes;

    public static void checkType(String apkFile) {

        try (ZipFile zipFile = new ZipFile(new File(apkFile))) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryName = entry.getName();

                if (entryName.equals("AndroidManifest.xml")) {
                    hasMF = true;
                } else if (entryName.equals("resources.arsc")) {
                    hasArsc = true;
                } else if (entryName.equals("BundleConfig.pb")) {
                    hasBundle = true;
                } else if (entryName.startsWith("base/") && entryName.endsWith("resources.pb")) {
                    hasRes = true;
                }    
                if (hasMF && hasArsc) {
                    isApk = true;
                    break;
                }
                if (hasBundle && hasRes) {
                    isAab = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isApkFile(String path) {
        checkType(path);
        return (isApk);
    }   
    
    public static boolean isAabFile(String path) {
        checkType(path);
        return (isAab);
    }   
}
package com.xapps.utility.xsigner;

import java.nio.ByteBuffer;

class HexEncoding {
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    private HexEncoding() {
    }

    public static String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public static String encode(byte[] bArr, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder(i2 * 2);
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i + i3];
            stringBuilder.append(HEX_DIGITS[(b >>> 4) & 15]);
            stringBuilder.append(HEX_DIGITS[b & 15]);
        }
        return stringBuilder.toString();
    }
}

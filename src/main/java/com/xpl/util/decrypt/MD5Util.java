package com.xpl.util.decrypt;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Author: lixiang
 * @Date: 2019-8-4 16:34
 */
public class MD5Util {
    protected static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messagedigest = null;

    public MD5Util() {
    }

    public static String getFileMD5String(File file) throws IOException {
        InputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        boolean var3 = false;

        int numRead;
        while ((numRead = fis.read(buffer)) > 0) {
            messagedigest.update(buffer, 0, numRead);
        }

        fis.close();
        return bufferToHex(messagedigest.digest());
    }

    public static String getStringMD5(String str) {
        byte[] buffer = null;

        try {
            buffer = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        messagedigest.update(buffer);
        return bufferToHex(messagedigest.digest());
    }

    public static String getStringMD5(String str, String input_charset) {
        byte[] buffer = null;

        try {
            buffer = str.getBytes(input_charset);
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

        messagedigest.update(buffer);
        return bufferToHex(messagedigest.digest());
    }

    public static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;

        for (int l = m; l < k; ++l) {
            appendHexPair(bytes[l], stringbuffer);
        }

        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 240) >> 4];
        char c1 = hexDigits[bt & 15];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var1) {
            var1.printStackTrace();
        }

    }
}


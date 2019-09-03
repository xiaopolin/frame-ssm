package com.xpl.util.decrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class Base64Util {
    private static char[] base64Code = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] base64Decode = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 63, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static String encodeBase64File(String path)
            throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    public static String encode(byte[] b) {
        int code = 0;

        StringBuffer sb = new StringBuffer((b.length - 1) / 3 << 6);
        for (int i = 0; i < b.length; i++) {
            code |= b[i] << 16 - i % 3 * 8 & 255 << 16 - i % 3 * 8;
            if ((i % 3 == 2) || (i == b.length - 1)) {
                sb.append(base64Code[((code & 0xFC0000) >>> 18)]);
                sb.append(base64Code[((code & 0x3F000) >>> 12)]);
                sb.append(base64Code[((code & 0xFC0) >>> 6)]);
                sb.append(base64Code[(code & 0x3F)]);
                code = 0;
            }
        }
        if (b.length % 3 > 0) {
            sb.setCharAt(sb.length() - 1, '=');
        }
        if (b.length % 3 == 1) {
            sb.setCharAt(sb.length() - 2, '=');
        }
        return sb.toString();
    }

    public static byte[] decode(String code) {
        if (code == null) {
            return new byte[0];
        }
        int len = code.length();
        if (len % 4 != 0) {
            throw new IllegalArgumentException("Base64Util string length must be 4*n");
        }
        if (code.length() == 0) {
            return new byte[0];
        }
        int pad = 0;
        if (code.charAt(len - 1) == '=') {
            pad++;
        }
        if (code.charAt(len - 2) == '=') {
            pad++;
        }
        int retLen = len / 4 * 3 - pad;

        byte[] ret = new byte[retLen];
        for (int i = 0; i < len; i += 4) {
            int j = i / 4 * 3;
            char ch1 = code.charAt(i);
            char ch2 = code.charAt(i + 1);
            char ch3 = code.charAt(i + 2);
            char ch4 = code.charAt(i + 3);
            int tmp = base64Decode[ch1] << 18 | base64Decode[ch2] << 12 | base64Decode[ch3] << 6 | base64Decode[ch4];
            ret[j] = ((byte) ((tmp & 0xFF0000) >> 16));
            if (i < len - 4) {
                ret[(j + 1)] = ((byte) ((tmp & 0xFF00) >> 8));
                ret[(j + 2)] = ((byte) (tmp & 0xFF));
            } else {
                if (j + 1 < retLen) {
                    ret[(j + 1)] = ((byte) ((tmp & 0xFF00) >> 8));
                }
                if (j + 2 < retLen) {
                    ret[(j + 2)] = ((byte) (tmp & 0xFF));
                }
            }
        }
        return ret;
    }

    public static String encode(String str) {
        if ((str == null) || ("".equals(str))) {
            return "";
        }
        try {
            byte[] bs = str.getBytes("UTF-8");
            return encode(bs);
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        }
        return null;
    }

    public static String decode2Str(String str) {
        byte[] bs = decode(str);
        try {
            return new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static InputStream toInputStream(String base64string) {
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception localException) {
        }
        return stream;
    }
}
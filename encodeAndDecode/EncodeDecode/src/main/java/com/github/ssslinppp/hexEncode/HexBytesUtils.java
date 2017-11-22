package com.github.ssslinppp.hexEncode;

/**
 * Description：十六进制编码与解码的实现<br/>
 * <p>
 * 测试类：{@code HexBytesUtilsTest.java}
 */
public final class HexBytesUtils {

    /**
     * 16进制编码
     *
     * @param bytes
     * @return
     */
    public static String bytes2hex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            boolean negative = false; //是否为负数
            if (b < 0) {
                negative = true;
            }

            int inte = Math.abs(b);

            if (negative) {//负数会转成正数
                inte = inte | 0x80;
            }

            String temp = Integer.toHexString(inte & 0xFF);
            if (temp.length() == 1) {
                hex.append("0");
            }
            hex.append(temp.toLowerCase());
        }
        return hex.toString();
    }

    /**
     * 16进制解码
     *
     * @param hex
     * @return
     */
    public static byte[] hex2bytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i = i + 2) {
            String subStr = hex.substring(i, i + 2);
            boolean negative = false; //是否为负数
            int inte = Integer.parseInt(subStr, 16);
            if (inte > 127) negative = true;
            if (inte == 128) {
                inte = -128;
            } else if (negative) {
                inte = 0 - (inte & 0x7F);
            }

            byte b = (byte) inte;
            bytes[i / 2] = b;
        }
        return bytes;
    }
}






















package com.ssslinppp.ketama;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description：<br/>
 * User: liulin <br/>
 * Date: 2017/11/7 <br/>
 * Time: 14:26 <br/>
 * Email: liulin@cmss.chinamobile.com <br/>
 * To change this template use File | Settings | File Templates.
 */
public enum HashAlgorithm {
    KETAMA_HASH;
    
    private static final String UTF_8 = "UTF-8";
    private static final String MD5 = "MD5";

    /**
     * @param digest 摘要
     * @param nTime
     * @return
     */
    public long hash(byte[] digest, int nTime) {
        long rv = ((long) (digest[3 + nTime * 4] & 0xFF) << 24)
                | ((long) (digest[2 + nTime * 4] & 0xFF) << 16)
                | ((long) (digest[1 + nTime * 4] & 0xFF) << 8)
                | (digest[0 + nTime * 4] & 0xFF);
        return rv & 0xffffffffL; /* Truncate to 32-bits */
    }

    /**
     * 获取Key的MD5值
     *
     * @param key
     * @return
     */
    public byte[] computeMd5(String key) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = key.getBytes(UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + key, e);
        }
        md5.update(keyBytes);
        return md5.digest();
    }
}

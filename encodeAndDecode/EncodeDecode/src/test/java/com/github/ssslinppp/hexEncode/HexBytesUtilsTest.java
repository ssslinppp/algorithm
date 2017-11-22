package com.github.ssslinppp.hexEncode;

/**
 * Description：<br/>
 * User: liulin <br/>
 * Date: 2017/11/22 <br/>
 * Time: 11:05 <br/>
 * Email: liulin@cmss.chinamobile.com <br/>
 * To change this template use File | Settings | File Templates.
 */
public class HexBytesUtilsTest {
    @org.junit.Test
    public void bytes2hex() throws Exception {
        byte[] helloBytes = "Hello,world".getBytes("UTF-8");
        System.out.println("\"Hello,world\".getBytes():" + helloBytes);

        //编码
        String hexString = HexBytesUtils.bytes2hex(helloBytes);
        System.out.println("十六进制编码结果:" + hexString);

        //解码
        byte[] bytes = HexBytesUtils.hex2bytes(hexString);
        System.out.println("十六进制解码结果:" + new String(bytes, "UTF-8"));
    }


}

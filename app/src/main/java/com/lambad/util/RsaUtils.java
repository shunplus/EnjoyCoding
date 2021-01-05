package com.lambad.util;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RsaUtils {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    //公钥
    public static String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnsK+ei3B1sp32s3oCCDmuPUBy/1kDURk+dgYJWc21oFySuJlrgdaV7a55EIowgHoKbno6DALa1q9p0udTm3tcXlVoo8hD8Id4d6d5lbIndTjhAIU4bFKZXQAddAFWA8FI5ye9a0IFHZbPdZzkUXpglwVHiRRhjWKgNiQUm9aioQIDAQAB";
    public static String PrivateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKewr56LcHWynfazegIIOa49QHL/WQNRGT52BglZzbWgXJK4mWuB1pXtrnkQijCAegpuejoMAtrWr2nS51Obe1xeVWijyEPwh3h3p3mVsid1OOEAhThsUpldAB10AVYDwUjnJ71rQgUdls91nORRemCXBUeJFGGNYqA2JBSb1qKhAgMBAAECgYBZ5SkwiDXgksGF6E8m2bv6QVHUlDjmw0oSJvkzeBYEIRSffyCX9oHP3BwlQI2kVKuX/gO+LIQ3RtHAnFRwiLCHRIoosc6bpkF3YvpuMsq0YOgaOynkvnqusmhaPg2HiVGIenG9cCZ2/skjHZDYUxaJ6H6qijl1TqIlhhlcQjyhdQJBANnUsNUbwZdm5BAq35Goa4ROTXQvg+R1HziYgcH1F7Th/bkLbALQTbjNzPaJMCevweeMOJKTrjCBwgl8vtAPHPcCQQDFEtHhBs8C7Q0vAng9o6esL4VpBeVRrcy0ut/qvTzhiyvgh1Ruxucl8dKcbXuTdnPRcjOWDNFpfPmKhTsz708nAkEApI6lq8hVHjAvV/tbe6j02DVjE8OVarAEDvg/BixGZXJX/0OerB16qYJnNVN8Gm8PGAH49tozvBc6Y3pml7zjDwJAI8yMZaU3+58WMwDu2Lue3M9xG6ffhVveAMKlpF2l7o66dLoAo3LsUF9CU/orZZkx5s0pA8vmSpRSPFCBdai2JwJARnZm99UJMwztaU4AaBT8SF2QBa7Gxaa39XYxTZOGTgvintnWFeyyxPuosavXOBQHHAc54vJ2snwi/9WxoIURkQ==";

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decode(publicKey.getBytes(), Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        /// 安卓这里是这样的
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // 在服务端使用这个标准
//        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        //  服务端 java代码
//        return new String(Base64.encode((encryptedData)));
        // Android  获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        return new String(Base64.encode(encryptedData, Base64.DEFAULT));

    }

}
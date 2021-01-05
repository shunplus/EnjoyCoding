package com.lambad.util;

import android.util.Base64;

import com.lambad.util.aessp.PLog;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtils {
    public static final String password = "pigxpigxpigxpigx"; //你的密钥
    private final static String IV = "pigxpigxpigxpigx"; //你的偏移量
    private static final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";//AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private static final String AES = "AES";//AES 加密
    private static final String SHA1PRNG = "SHA1PRNG";//// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法，现已可以不使用

    /**
     * 加密
     *
     * @param cleartext 需加密字段
     * @return
     */
    public static String encrypt(String cleartext) {
        return encrypt(cleartext.getBytes());
    }

    /**
     * 加密
     *
     * @param cleartext 需加密字段
     * @return
     */
    public static String encrypt(byte[] cleartext) {
        IvParameterSpec zeroIv = new IvParameterSpec(IV.getBytes());
        SecretKeySpec key = new SecretKeySpec(password.getBytes(), AES);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(cleartext);
            return new String(Base64.encode(encryptedData, Base64.DEFAULT));
        } catch (NoSuchAlgorithmException e) {
            PLog.e(e.getMessage());
        } catch (NoSuchPaddingException e) {
            PLog.e(e.getMessage());
        } catch (BadPaddingException e) {
            PLog.e(e.getMessage());
        } catch (InvalidKeyException e) {
            PLog.e(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            PLog.e(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            PLog.e(e.getMessage());
        }
        return null;
    }


    /**
     * 解密
     *
     * @param encrypted 密文
     * @return
     */
    public static String decrypt(String encrypted) {
        byte[] byteMi = Base64.decode(encrypted, Base64.DEFAULT);
        IvParameterSpec zeroIv = new IvParameterSpec(IV.getBytes());
        SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData);
        } catch (NoSuchAlgorithmException e) {
            PLog.e(e.getMessage());
        } catch (NoSuchPaddingException e) {
            PLog.e(e.getMessage());
        } catch (BadPaddingException e) {
            PLog.e(e.getMessage());
        } catch (InvalidKeyException e) {
            PLog.e(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            PLog.e(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            PLog.e(e.getMessage());
        }
        return null;
    }
}
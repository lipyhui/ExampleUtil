package com.android.lipy.utils.safe.data;

import android.util.Base64;

import com.android.lipy.utils.constant.EnumConstant;

import java.io.File;

import kotlin.text.Charsets;

@SuppressWarnings(value = {"unused", "WeakerAccess"})
public class EncryptUtils {
    private static final EncryptUtils instance = new EncryptUtils();

    private EncryptUtils() {
    }

    public static EncryptUtils getInstance() {
        return instance;
    }

    /**
     * Return the hex string of MD5 encryption.
     *
     * @param data The data.
     * @return the hex string of MD5 encryption
     */
    public String encryptMD5ToString(String data) {
        return (data == null || data.isEmpty()) ? "" : encryptMD5ToString(data.getBytes(Charsets.UTF_8));
    }

    /**
     * Return the hex string of MD5 encryption.
     *
     * @param data The data.
     * @return the hex string of MD5 encryption
     */
    public String encryptMD5ToString(byte[] data) {
        return EncryptInternal.INSTANCE.bytes2HexString(encryptMD5(data));
    }

    /**
     * Return the bytes of MD5 encryption.
     *
     * @param data The data.
     * @return the bytes of MD5 encryption
     */
    public byte[] encryptMD5(byte[] data) {
        return EncryptInternal.INSTANCE.hashTemplate(data, "MD5");
    }

    /**
     * Return the hex string of file's MD5 encryption.
     *
     * @param filePath The path of file.
     * @return the hex string of file's MD5 encryption
     */
    public String encryptMD5File2String(String filePath) {
        File file = EncryptInternal.INSTANCE.isSpace(filePath) ? null : new File(filePath);
        return encryptMD5File2String(file);
    }

    /**
     * Return the bytes of file's MD5 encryption.
     *
     * @param filePath The path of file.
     * @return the bytes of file's MD5 encryption
     */
    public byte[] encryptMD5File(String filePath) {
        File file = EncryptInternal.INSTANCE.isSpace(filePath) ? null : new File(filePath);
        return encryptMD5File(file);
    }

    /**
     * Return the hex string of file's MD5 encryption.
     *
     * @param file The file.
     * @return the hex string of file's MD5 encryption
     */
    public String encryptMD5File2String(File file) {
        return EncryptInternal.INSTANCE.bytes2HexString(encryptMD5File(file));
    }

    /**
     * Return the bytes of file's MD5 encryption.
     *
     * @param file The file.
     * @return the bytes of file's MD5 encryption
     */
    public byte[] encryptMD5File(File file) {
        return EncryptInternal.INSTANCE.encryptMD5File(file);
    }

    /**
     * Return the hex string of SHA encryption.
     *
     * @param data The data.
     * @param sha  The sha type. detail see {@link EnumConstant.SHA}
     * @return the hex string of SHA encryption
     */
    public String encryptSHAToString(String data, @EnumConstant.SHA String sha) {
        return (data == null || data.isEmpty()) ? "" : encryptSHAToString(data.getBytes(Charsets.UTF_8), sha);
    }

    /**
     * Return the hex string of SHA encryption.
     *
     * @param data The data.
     * @param sha  The sha type. detail see {@link EnumConstant.SHA}
     * @return the hex string of SHA encryption
     */
    public String encryptSHAToString(byte[] data, @EnumConstant.SHA String sha) {
        return EncryptInternal.INSTANCE.bytes2HexString(encryptSHA(data, sha));
    }

    /**
     * Return the bytes of SHA encryption.
     *
     * @param data The data.
     * @param sha  The sha type. detail see {@link EnumConstant.SHA}
     * @return the bytes of SHA encryption
     */
    public byte[] encryptSHA(byte[] data, @EnumConstant.SHA String sha) {
        return EncryptInternal.INSTANCE.hashTemplate(data, sha);
    }

    /**
     * Return the hex string of HmacMD5 encryption.
     *
     * @param data The data.
     * @param key  The key.
     * @return the hex string of HmacMD5 encryption
     */
    public String encryptHmacMD5ToString(String data, String key) {
        return (data == null || data.isEmpty() || key == null || key.isEmpty()) ? "" :
                encryptHmacMD5ToString(data.getBytes(Charsets.UTF_8), key.getBytes(Charsets.UTF_8));
    }

    /**
     * Return the hex string of HmacMD5 encryption.
     *
     * @param data The data.
     * @param key  The key.
     * @return the hex string of HmacMD5 encryption
     */
    public String encryptHmacMD5ToString(byte[] data, byte[] key) {
        return EncryptInternal.INSTANCE.bytes2HexString(encryptHmacMD5(data, key));
    }

    /**
     * Return the bytes of HmacMD5 encryption.
     *
     * @param data The data.
     * @param key  The key.
     * @return the bytes of HmacMD5 encryption
     */
    public byte[] encryptHmacMD5(byte[] data, byte[] key) {
        return EncryptInternal.INSTANCE.hmacTemplate(data, key, "HmacMD5");
    }

    /**
     * Return the hex string of HmacSHA encryption.
     *
     * @param data    The data.
     * @param key     The key.
     * @param hmacSha The sha type. detail see {@link EnumConstant.HMAC_SHA}
     * @return the hex string of HmacSHA encryption
     */
    public String encryptHmacSHAToString(String data, String key, @EnumConstant.HMAC_SHA String hmacSha) {
        return (data == null || data.length() == 0 || key == null || key.length() == 0) ? "" :
                encryptHmacSHAToString(data.getBytes(Charsets.UTF_8), key.getBytes(Charsets.UTF_8), hmacSha);
    }

    /**
     * Return the hex string of HmacSHA encryption.
     *
     * @param data    The data.
     * @param key     The key.
     * @param hmacSha The sha type. detail see {@link EnumConstant.HMAC_SHA}
     * @return the hex string of HmacSHA encryption
     */
    public String encryptHmacSHAToString(byte[] data, byte[] key, @EnumConstant.HMAC_SHA String hmacSha) {
        return EncryptInternal.INSTANCE.bytes2HexString(encryptHmacSHA(data, key, hmacSha));
    }

    /**
     * Return the bytes of HmacSHA encryption.
     *
     * @param data    The data.
     * @param key     The key.
     * @param hmacSha The sha type. detail see {@link EnumConstant.HMAC_SHA}
     * @return the bytes of HmacSHA encryption
     */
    public byte[] encryptHmacSHA(byte[] data, byte[] key, @EnumConstant.HMAC_SHA String hmacSha) {
        return EncryptInternal.INSTANCE.hmacTemplate(data, key, hmacSha);
    }

    /**
     * Return the hex string of DES encryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the hex string of DES encryption
     */
    public String encryptDES2String(byte[] data, byte[] key, String transformation, byte[] iv) {
        return EncryptInternal.INSTANCE.bytes2HexString(encryptDES(data, key, transformation, iv));
    }

    /**
     * Return the bytes of DES encryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of DES encryption
     */
    public byte[] encryptDES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return EncryptInternal.INSTANCE.symmetricTemplate(data, key, "DES", transformation, iv, true);
    }

    /**
     * Return the bytes of DES decryption for hex string.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of DES decryption for hex string
     */
    public byte[] decryptHexStringDES(String data, byte[] key, String transformation, byte[] iv) {
        return decryptDES(EncryptInternal.INSTANCE.hexString2Bytes(data), key, transformation, iv);
    }

    /**
     * Return the bytes of DES decryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of DES decryption
     */
    public byte[] decryptDES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return EncryptInternal.INSTANCE.symmetricTemplate(data, key, "DES", transformation, iv, false);
    }

    /**
     * Return the bytes of DES decryption for hex string.
     *
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of DES decryption for hex string
     */
    public String encrypt3DES2HexString(byte[] data, byte[] key, String transformation, byte[] iv) {
        return EncryptInternal.INSTANCE.bytes2HexString(encrypt3DES(data, key, transformation, iv));
    }

    /**
     * Return the bytes of 3DES encryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of 3DES encryption
     */
    public byte[] encrypt3DES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return EncryptInternal.INSTANCE.symmetricTemplate(data, key, "DESede", transformation, iv, true);
    }

    /**
     * Return the bytes of 3DES decryption for hex string.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of 3DES decryption for hex string
     */
    public byte[] decryptHexString3DES(String data, byte[] key, String transformation, byte[] iv) {
        return decrypt3DES(EncryptInternal.INSTANCE.hexString2Bytes(data), key, transformation, iv);
    }

    /**
     * Return the bytes of 3DES decryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of 3DES decryption
     */
    public byte[] decrypt3DES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return EncryptInternal.INSTANCE.symmetricTemplate(data, key, "DESede", transformation, iv, false);
    }

    /**
     * Return the hex string of AES encryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the hex string of AES encryption
     */
    public String encryptAES2HexString(byte[] data, byte[] key, String transformation, byte[] iv) {
        return EncryptInternal.INSTANCE.bytes2HexString(encryptAES(data, key, transformation, iv));
    }

    /**
     * Return the bytes of AES encryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of AES encryption
     */
    public byte[] encryptAES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return EncryptInternal.INSTANCE.symmetricTemplate(data, key, "AES", transformation, iv, true);
    }


    /**
     * Return the bytes of AES decryption for hex string.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of AES decryption for hex string
     */
    public byte[] decryptHexStringAES(String data, byte[] key, String transformation, byte[] iv) {
        return decryptAES(EncryptInternal.INSTANCE.hexString2Bytes(data), key, transformation, iv);
    }

    /**
     * Return the bytes of AES decryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
     * @param iv             The buffer with the IV. The contents of the
     *                       buffer are copied to protect against subsequent modification.
     * @return the bytes of AES decryption
     */
    public byte[] decryptAES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return EncryptInternal.INSTANCE.symmetricTemplate(data, key, "AES", transformation, iv, false);
    }

    /**
     * Return the hex string of RSA encryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param isPublicKey    True to use public key, false to use private key.
     * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
     * @return the hex string of RSA encryption
     */
    public String encryptRSA2HexString(byte[] data, byte[] key, boolean isPublicKey, String transformation) {
        return EncryptInternal.INSTANCE.bytes2HexString(encryptRSA(data, key, isPublicKey, transformation));
    }

    /**
     * Return the bytes of RSA encryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param isPublicKey    True to use public key, false to use private key.
     * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
     * @return the bytes of RSA encryption
     */
    public byte[] encryptRSA(byte[] data, byte[] key, boolean isPublicKey, String transformation) {
        return EncryptInternal.INSTANCE.rsaTemplate(data, key, isPublicKey, transformation, true);
    }

    /**
     * Return the bytes of RSA decryption for hex string.
     *
     * @param data           The data.
     * @param key            The key.
     * @param isPublicKey    True to use public key, false to use private key.
     * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
     * @return the bytes of RSA decryption for hex string
     */
    public byte[] decryptHexStringRSA(String data, byte[] key, boolean isPublicKey, String transformation) {
        return decryptRSA(EncryptInternal.INSTANCE.hexString2Bytes(data), key, isPublicKey, transformation);
    }

    /**
     * Return the bytes of RSA decryption.
     *
     * @param data           The data.
     * @param key            The key.
     * @param isPublicKey    True to use public key, false to use private key.
     * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
     * @return the bytes of RSA decryption
     */
    public byte[] decryptRSA(byte[] data, byte[] key, boolean isPublicKey, String transformation) {
        return EncryptInternal.INSTANCE.rsaTemplate(data, key, isPublicKey, transformation, false);
    }

    /**
     * Return the bytes of Base64-encode
     *
     * @param data The data.
     * @return the bytes of Base64-encode
     */
    public byte[] base64Encode(byte[] data) {
        return Base64.encode(data, Base64.NO_WRAP);
    }

    /**
     * Return the bytes of Base64-decode
     *
     * @param data The data.
     * @return the bytes of Base64-decode
     */
    public byte[] base64Decode(byte[] data) {
        return Base64.decode(data, Base64.NO_WRAP);
    }
}

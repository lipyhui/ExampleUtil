# EncryptUtils
&emsp;&emsp;EncryptUtils是一个数据加密界面的工具类。使用方式为EncryptUtils.getInstance().xxx(x)方式。

# 目录
- [encryptMD5ToString --> MD5加密](#encryptMD5ToString)
- [encryptMD5 --> MD5加密](#encryptMD5)
- [encryptMD5File2String --> MD5加密](#encryptMD5File2String)
- [encryptMD5File --> MD5加密](#encryptMD5File)
- [encryptSHAToString --> SHA加密](#encryptSHAToString)
- [encryptSHA --> SHA加密](#encryptSHA)
- [encryptHmacMD5ToString --> HmacMD5加密](#encryptHmacMD5ToString)
- [encryptHmacMD5 --> HmacMD5加密](#encryptHmacMD5)
- [encryptHmacSHAToString --> HmacSHA加密](#encryptHmacSHAToString)
- [encryptHmacSHA --> HmacSHA加密](#encryptHmacSHA)
- [encryptDES2String --> DES加密](#encryptDES2String)
- [encryptDES --> DES加密](#encryptDES)
- [decryptHexStringDES --> hex数据DES解密](#decryptHexStringDES)
- [decryptDES --> DES解密](#decryptDES)
- [encrypt3DES2HexString --> 3DES加密](#encrypt3DES2HexString)
- [encrypt3DES --> 3DES加密](#encrypt3DES)
- [decryptHexString3DES --> hex数据3DES解密](#decryptHexString3DES)
- [decrypt3DES --> 3DES解密](#decrypt3DES)
- [encryptAES2HexString --> AES加密并返回hex字符串](#encryptAES2HexString)
- [encryptAES --> AES加密](#encryptAES)
- [decryptHexStringAES --> hex数据AES解密](#decryptHexStringAES)
- [decryptAES --> AES解密](#decryptAES)
- [encryptRSA2HexString --> RSA加密并返回hex字符串](#encryptRSA2HexString)
- [encryptRSA -->  RSA加密](#encryptRSA)
- [decryptHexStringRSA --> hex数据RSA解密](#decryptHexStringRSA)
- [decryptRSA --> RSA解密](#decryptRSA)
- [base64Encode --> base64加密](#base64Encode)
- [base64Decode --> base64解密](#base64Decode)

## encryptMD5ToString
&emsp;&emsp;进行MD5加密，返回加密的字符串。
```
public String encryptMD5ToString(String data)
public String encryptMD5ToString(byte[] data)
```

## encryptMD5
&emsp;&emsp;进行MD5加密，返回加密的字节数组。
```
public byte[] encryptMD5(byte[] data)
```

## encryptMD5File2String
&emsp;&emsp;进行MD5加密，传参为文件路径或者文件，返回加密的字符串。
```
public String encryptMD5File2String(String filePath)
public String encryptMD5File2String(File file)
```

## encryptMD5File
&emsp;&emsp;进行MD5加密，传参为文件路径或者文件，返回加密的字节数组。
```
public byte[] encryptMD5File(String filePath)
public byte[] encryptMD5File(File file) 
```

## encryptSHAToString
&emsp;&emsp;进行SHA加密，加密类型通过sha参数设置（包含SHA1, SHA224, SHA256, SHA384, SHA512，例如：EnumConstant.SHA1），返回加密的字符串。
```
public String encryptSHAToString(String data, @EnumConstant.SHA String sha)
public String encryptSHAToString(byte[] data, @EnumConstant.SHA String sha)
```

## encryptSHA
&emsp;&emsp;进行SHA加密，加密类型通过sha参数设置（包含SHA1, SHA224, SHA256, SHA384, SHA512，例如：EnumConstant.SHA1），返回加密的字节数组。
```
public byte[] encryptSHA(byte[] data, @EnumConstant.SHA String sha)
```

## encryptHmacMD5ToString
&emsp;&emsp;进行HmacMD5加密，返回加密的字符串。
```
public String encryptHmacMD5ToString(String data, String key)
public String encryptHmacMD5ToString(byte[] data, byte[] key)
```

## encryptHmacMD5
&emsp;&emsp;进行HmacMD5加密，返回加密的字节数组。
```
public byte[] encryptHmacMD5(byte[] data, byte[] key)
```

## encryptHmacSHAToString
&emsp;&emsp;进行HmacSHA加密，加密类型通过hmacSha参数设置（包含HMAC_SHA1, HMAC_SHA224, HMAC_SHA256, HMAC_SHA384, HMAC_SHA51，例如：EnumConstant.HMAC_SHA1。其中HmacSHA224、HMAC_SHA384有问题暂未开发），返回加密的字符串。
```
public String encryptHmacSHAToString(String data, String key, @EnumConstant.HMAC_SHA String hmacSha)
public String encryptHmacSHAToString(byte[] data, byte[] key, @EnumConstant.HMAC_SHA String hmacSha)
```

## encryptHmacSHA
&emsp;&emsp;进行HmacSHA加密，加密类型通过hmacSha参数设置（包含HMAC_SHA1, HMAC_SHA224, HMAC_SHA256, HMAC_SHA384, HMAC_SHA51，例如：EnumConstant.HMAC_SHA1。其中HmacSHA224、HMAC_SHA384有问题暂未开发），返回加密的字节数组。
```
public byte[] encryptHmacSHA(byte[] data, byte[] key, @EnumConstant.HMAC_SHA String hmacSha)
```

## encryptDES2String
&emsp;&emsp;进行DES加密（目前有问题，暂未开放），返回加密的字符串。
```
private String encryptDES2String(byte[] data, byte[] key, String transformation, byte[] iv)
```

## encryptDES
&emsp;&emsp;进行DES加密（目前有问题，暂未开放），返回加密的字节数组。
```
private byte[] encryptDES(byte[] data, byte[] key, String transformation, byte[] iv)
```

## decryptHexStringDES
&emsp;&emsp;hex数据进行DES解密（目前有问题，暂未开放），返回解密的字节数组。
```
private byte[] decryptHexStringDES(String data, byte[] key, String transformation, byte[] iv)
```

## decryptDES
&emsp;&emsp;进行DES解密（目前有问题，暂未开放），返回解密的字节数组。
```
private byte[] decryptDES(byte[] data, byte[] key, String transformation, byte[] iv)
```

## encrypt3DES2HexString
&emsp;&emsp;进行3DES加密（目前有问题，暂未开放），返回加密的字符串。
```
private String encrypt3DES2HexString(byte[] data, byte[] key, String transformation, byte[] iv)
```

## encrypt3DES
&emsp;&emsp;进行3DES加密（目前有问题，暂未开放），返回加密的字节数组。
```
private byte[] encrypt3DES(byte[] data, byte[] key, String transformation, byte[] iv)
```

## decryptHexString3DES
&emsp;&emsp;hex数据进行3DES解密（目前有问题，暂未开放），返回解密的字节数组。
```
private byte[] decryptHexString3DES(String data, byte[] key, String transformation, byte[] iv)
```

## decrypt3DES
&emsp;&emsp;进行3DES加密（目前有问题，暂未开放），返回解密的字节数组。
```
private byte[] decrypt3DES(byte[] data, byte[] key, String transformation, byte[] iv)
```

## encryptAES2HexString
&emsp;&emsp;进行AES加密，返回加密的字符串。
```
public String encryptAES2HexString(byte[] data, byte[] key, String transformation, byte[] iv)
```

## encryptAES
&emsp;&emsp;进行AES加密，返回加密的字符串。
```
public byte[] encryptAES(byte[] data, byte[] key, String transformation, byte[] iv) 
```

## decryptHexStringAES
&emsp;&emsp;hex数据进行AES解密，返回解密的字符串。
```
public byte[] decryptHexStringAES(String data, byte[] key, String transformation, byte[] iv)
```

## decryptAES
&emsp;&emsp;进行AES解密，返回解密的字节数组。
```
private byte[] decryptDES(byte[] data, byte[] key, String transformation, byte[] iv)
```

## encryptRSA2HexString
&emsp;&emsp;进行RSA加密，返回加密的 hex 字符串。
```
public String encryptRSA2HexString(byte[] data, byte[] key, boolean isPublicKey, String transformation)
```

## encryptRSA
&emsp;&emsp;进行RSA加密，返回加密的字节数组。
```
public byte[] encryptRSA(byte[] data, byte[] key, boolean isPublicKey, String transformation)
```

## decryptHexStringRSA
&emsp;&emsp;hex 数据进行RSA解密，返回解密的字符串。
```
public byte[] decryptHexStringRSA(String data, byte[] key, boolean isPublicKey, String transformation)
```

## decryptRSA
&emsp;&emsp;进行RSA解密，返回解密的字节数组。
```
public byte[] decryptRSA(byte[] data, byte[] key, boolean isPublicKey, String transformation)
```

## base64Encode
&emsp;&emsp;进行Base64加密，返回加密的字节数组。
```
public byte[] base64Encode(byte[] data)
```

## base64Decode
&emsp;&emsp;进行Base64解密，返回解密的字节数组。
```
public byte[] base64Decode(byte[] data)
```
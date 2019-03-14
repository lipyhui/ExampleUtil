package com.android.lipy.utils.constant

import android.support.annotation.StringDef

object EnumConstant {

    /**
     * SHA
     */
    @StringDef(SHA1, SHA224, SHA256, SHA384, SHA512)
    annotation class SHA

    const val SHA1 = "SHA-1"
    const val SHA224 = "SHA224"
    const val SHA256 = "SHA-256"
    const val SHA384 = "SHA-384"
    const val SHA512 = "SHA-512"

    /**
     * HmacSHA
     */
    @StringDef(HMAC_SHA1, HMAC_SHA224, HMAC_SHA256, HMAC_SHA384, HMAC_SHA512)
    annotation class HMAC_SHA

    const val HMAC_SHA1 = "HmacSHA1"
    private const val HMAC_SHA224 = "HmacSHA224"    //Due to the encrypted value not temporarily not open
    const val HMAC_SHA256 = "HmacSHA256"
    private const val HMAC_SHA384 = "HmacSHA384"    //Due to the encrypted value not temporarily not open
    const val HMAC_SHA512 = "HmacSHA512"
}
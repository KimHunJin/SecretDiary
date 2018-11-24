package com.dxmnd.secretdiary.utils

import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


@Throws(UnsupportedEncodingException::class,
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class)
fun encrypt(ivByte: ByteArray, keyByte: ByteArray, textByte: ByteArray): ByteArray {
    var ivSpec:AlgorithmParameterSpec = IvParameterSpec(ivByte)
    var newKey = SecretKeySpec(keyByte, "AES")
    var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec)
    return cipher.doFinal(textByte)
}

@Throws(UnsupportedEncodingException::class,
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class)
fun decrypt(ivByte: ByteArray, keyByte: ByteArray, textByte: ByteArray) : ByteArray {
    var ivSpec:AlgorithmParameterSpec = IvParameterSpec(ivByte)
    var newKey = SecretKeySpec(keyByte, "AES")
    var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec)
    return cipher.doFinal(textByte)
}
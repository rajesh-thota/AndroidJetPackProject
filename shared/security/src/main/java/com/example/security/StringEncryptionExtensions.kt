package com.example.security

fun String.encrypt(): ByteArray {
    return Utils.encrypt(this) ?: byteArrayOf()
}

fun ByteArray.decrypt(): String {
    return Utils.decrypt(this).orEmpty()
}
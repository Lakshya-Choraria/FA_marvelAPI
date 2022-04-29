package com.example.android.fa_marvelapi.util

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object{
        const val BASE_URL = "https://gateway.marvel.com"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "5156f3c5a51068c552fc3a064df038e3"
        const val Private_KEY = "409d526b5d77eef304b1b2aeadb9fbdb60c3ae6b"
        const val limit = "20"
        fun hash():String{
            val input = "$timeStamp$Private_KEY$API_KEY";
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }
}
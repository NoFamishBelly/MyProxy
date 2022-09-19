package com.example.appcappappdemo.utils

import com.example.appcappappdemo.BuildConfig
import java.security.MessageDigest

object MD5Utils {

    const val MD5 = "MD5"

    fun md5s(plainText: String?): String {

        plainText?.let { text ->
            try {
                val md = MessageDigest.getInstance(MD5)
                md.update(text.toByteArray())
                val b = md.digest()

                var i: Int

                val buf = StringBuilder("")
                for (offset in b.indices) {
                    i = b[offset].toInt()
                    if (i < 0) {
                        i += 256
                    }
                    if (i < 16) {
                        buf.append("0")
                    }
                    buf.append(Integer.toHexString(i))
                }
                return buf.toString()
            } catch (e: Exception) {
                if (BuildConfig.isDebug) {
                    e.printStackTrace()
                }
                return text
            }
        }
        return ""
    }
}
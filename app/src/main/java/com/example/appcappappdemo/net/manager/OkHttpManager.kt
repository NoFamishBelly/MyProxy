package com.example.appcappappdemo.net.manager

import okhttp3.OkHttpClient
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object OkHttpManager {

    private const val TIME_OUT_CONNECT = 30L
    private const val TIME_OUT_READ = 30L
    private const val TIME_OUT_WRITE = 30L

    private lateinit var mOkHttpClient: OkHttpClient

    fun getOkHttpClient(): OkHttpClient {
        mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS)
            .sslSocketFactory(getNoCertsVerifySSLContext()!!.socketFactory)
            .build()
        return mOkHttpClient
    }


    private fun getNoCertsVerifySSLContext(): SSLContext? {
        val xtm: X509TrustManager = object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate?> {
                return arrayOfNulls(0)
            }
        }
        var sslContext: SSLContext? = null
        try {
            sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, arrayOf<TrustManager>(xtm), SecureRandom())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
        return sslContext
    }

}
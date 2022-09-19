package com.example.appcappappdemo.utils

import android.util.Log

object LogUtils {


    private const val TAG = "TAG_ZLZ"


    fun i(msg: String?, tag: String = TAG) {
        msg?.let {
            Log.i(tag, it)
        }
    }


}
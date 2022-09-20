package com.example.appcappappdemo.base.application

import android.app.Application
import com.example.appcappappdemo.net.manager.AppClient
import com.example.appcappappdemo.utils.SpUtils

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppClient.init(this)
        SpUtils.init(this)
    }
}
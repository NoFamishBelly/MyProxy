package com.example.appcappappdemo.net.manager

import android.content.Context
import com.example.appcappappdemo.BuildConfig
import com.example.appcappappdemo.aca.entity.request.BaseRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.net.listener.request.RequestCall
import com.example.appcappappdemo.net.listener.response.ResponseCallback
import com.example.appcappappdemo.net.listener.result.ResultCallback
import com.example.appcappappdemo.net.param.HttpParams

object AppClient {

    private lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context
    }

    fun pay(data: BaseRequestEntity, callback: ResultCallback<PayResponseEntity>?) {
        RequestCall<PayResponseEntity>(
            HttpParams(BuildConfig.baseUrl, data.dataMap),
            PayResponseEntity()
        ).enqueue(object : ResponseCallback<PayResponseEntity> {
            override fun onResponseSuccess(response: PayResponseEntity) {
                callback?.let {
                    it.onResultSuccess(response)
                }
            }

            override fun onResponseFail(errCode: String, errMsg: String) {
                callback?.let {
                    it.onResultFail(errCode, errMsg)
                }
            }
        })
    }

}
package com.example.appcappappdemo.net.manager

import android.content.Context
import com.example.appcappappdemo.BuildConfig
import com.example.appcappappdemo.aca.entity.request.BaseRequestEntity
import com.example.appcappappdemo.aca.entity.request.QueryRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.aca.entity.response.QueryResponseEntity
import com.example.appcappappdemo.net.listener.request.RequestCall
import com.example.appcappappdemo.net.listener.response.ResponseCallbackWrapper
import com.example.appcappappdemo.net.listener.result.ResultCallback
import com.example.appcappappdemo.net.param.HttpParams

object AppClient {

    private lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context
    }

    fun pay(requestParam: BaseRequestEntity, callback: ResultCallback<PayResponseEntity>?) {
        RequestCall<PayResponseEntity>(
            HttpParams(BuildConfig.baseUrl, requestParam.dataMap),
            PayResponseEntity()
        ).enqueue(ResponseCallbackWrapper<PayResponseEntity>(callback))
    }

    fun query(requestParam: QueryRequestEntity, callback: ResultCallback<QueryResponseEntity>?) {
        RequestCall<QueryResponseEntity>(
            HttpParams(BuildConfig.baseUrl, requestParam.dataMap),
            QueryResponseEntity()
        ).enqueue(ResponseCallbackWrapper<QueryResponseEntity>(callback))
    }

}
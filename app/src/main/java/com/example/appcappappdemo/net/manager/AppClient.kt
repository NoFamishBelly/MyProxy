package com.example.appcappappdemo.net.manager

import android.content.Context
import com.example.appcappappdemo.BuildConfig
import com.example.appcappappdemo.aca.entity.request.BaseRequestEntity
import com.example.appcappappdemo.aca.entity.request.QueryRequestEntity
import com.example.appcappappdemo.aca.entity.request.RefundRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.aca.entity.response.QueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundResponseEntity
import com.example.appcappappdemo.net.listener.request.RequestCall
import com.example.appcappappdemo.net.listener.response.ResponseCallbackWrapper
import com.example.appcappappdemo.net.listener.result.ResultCallback
import com.example.appcappappdemo.net.param.HttpParams
import com.example.appcappappdemo.utils.SpUtils

object AppClient {

    private const val BASE_UEL = "base_url"

    private lateinit var mContext: Context
    private lateinit var mBaseUrl: String

    fun init(context: Context) {
        mContext = context
        mBaseUrl = SpUtils.get(BASE_UEL, BuildConfig.baseUrl)
    }


    fun getBaseUrl() = mBaseUrl


    fun pay(requestParam: BaseRequestEntity, callback: ResultCallback<PayResponseEntity>?) {
        RequestCall<PayResponseEntity>(
            HttpParams(mBaseUrl, requestParam.dataMap),
            PayResponseEntity()
        ).enqueue(ResponseCallbackWrapper<PayResponseEntity>(callback))
    }

    fun query(requestParam: QueryRequestEntity, callback: ResultCallback<QueryResponseEntity>?) {
        RequestCall<QueryResponseEntity>(
            HttpParams(mBaseUrl, requestParam.dataMap),
            QueryResponseEntity()
        ).enqueue(ResponseCallbackWrapper<QueryResponseEntity>(callback))
    }

    fun refund(requestParam: RefundRequestEntity, callback: ResultCallback<RefundResponseEntity>?) {
        RequestCall<RefundResponseEntity>(
            HttpParams(mBaseUrl, requestParam.dataMap),
            RefundResponseEntity()
        ).enqueue(ResponseCallbackWrapper<RefundResponseEntity>(callback))
    }

}
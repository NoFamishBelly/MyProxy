package com.example.appcappappdemo.net.manager

import com.example.appcappappdemo.aca.api.PayApi
import com.example.appcappappdemo.aca.entity.request.PayRequestEntity
import com.example.appcappappdemo.aca.entity.request.QueryRequestEntity
import com.example.appcappappdemo.aca.entity.request.RefundQueryRequestEntity
import com.example.appcappappdemo.aca.entity.request.RefundRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.aca.entity.response.QueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundQueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundResponseEntity
import com.example.appcappappdemo.net.annotation.ApiHelper
import com.example.appcappappdemo.net.listener.response.ResponseCallbackWrapper
import com.example.appcappappdemo.net.listener.result.ResultCallback

object AppClient {
    private lateinit var mPayApi: PayApi

    fun init() {
        mPayApi = ApiHelper.getApi(PayApi::class.java)
    }


    fun pay(requestParam: PayRequestEntity, callback: ResultCallback<PayResponseEntity>?) {
        val map = requestParam.dataMap
        mPayApi.pay(
            map[PayRequestEntity.PARAM_BODY].toString(),
            map[PayRequestEntity.PARAM_DEVICE_INFO].toString(),
            map[PayRequestEntity.PARAM_MCH_CREATE_IP].toString(),
            map[PayRequestEntity.PARAM_MCH_ID].toString(),
            map[PayRequestEntity.PARAM_NONCE_STR].toString(),
            map[PayRequestEntity.PARAM_NOTIFY_URL].toString(),
            map[PayRequestEntity.PARAM_OUT_TRADE_NO].toString(),
            map[PayRequestEntity.PARAM_SERVICE].toString(),
            map[PayRequestEntity.PARAM_SIGN].toString(),
            map[PayRequestEntity.PARAM_SIGN_TYPE].toString(),
            map[PayRequestEntity.PARAM_TOTAL_FEE].toString(),
            map[PayRequestEntity.PARAM_VERSION].toString(),
            map[PayRequestEntity.PARAM_CHARSET].toString(),
            map[PayRequestEntity.PARAM_ATTACH].toString(),
            map[PayRequestEntity.PARAM_TIME_START].toString(),
            map[PayRequestEntity.PARAM_TIME_EXPIRE].toString()
        ).enqueue(ResponseCallbackWrapper<PayResponseEntity>(callback))
    }

    fun query(requestParam: QueryRequestEntity, callback: ResultCallback<QueryResponseEntity>?) {
        val map = requestParam.dataMap
        mPayApi.query(
            map[QueryRequestEntity.PARAM_MCH_ID].toString(),
            map[QueryRequestEntity.PARAM_NONCE_STR].toString(),
            map[QueryRequestEntity.PARAM_OUT_TRADE_NO].toString(),
            map[QueryRequestEntity.PARAM_SERVICE].toString(),
            map[QueryRequestEntity.PARAM_SIGN].toString(),
            map[QueryRequestEntity.PARAM_SIGN_TYPE].toString(),
            map[QueryRequestEntity.PARAM_VERSION].toString(),
            map[QueryRequestEntity.PARAM_CHARSET].toString()
        ).enqueue(ResponseCallbackWrapper<QueryResponseEntity>(callback))
    }

    fun refund(requestParam: RefundRequestEntity, callback: ResultCallback<RefundResponseEntity>?) {
        val map = requestParam.dataMap
        mPayApi.refund(
            map[RefundRequestEntity.PARAM_MCH_ID].toString(),
            map[RefundRequestEntity.PARAM_NONCE_STR].toString(),
            map[RefundRequestEntity.PARAM_OP_USER_ID].toString(),
            map[RefundRequestEntity.PARAM_OUT_REFUND_NO].toString(),
            map[RefundRequestEntity.PARAM_OUT_TRADE_NO].toString(),
            map[RefundRequestEntity.PARAM_REFUND_FEE].toString(),
            map[RefundRequestEntity.PARAM_SERVICE].toString(),
            map[RefundRequestEntity.PARAM_SIGN].toString(),
            map[RefundRequestEntity.PARAM_SIGN_TYPE].toString(),
            map[RefundRequestEntity.PARAM_TOTAL_FEE].toString(),
            map[RefundRequestEntity.PARAM_VERSION].toString(),
            map[RefundRequestEntity.PARAM_CHARSET].toString()
        ).enqueue(ResponseCallbackWrapper<RefundResponseEntity>(callback))
    }


    fun refundQuery(
        requestParam: RefundQueryRequestEntity,
        callback: ResultCallback<RefundQueryResponseEntity>?
    ) {
        val map = requestParam.dataMap
        mPayApi.refundQuery(
            map[RefundRequestEntity.PARAM_MCH_ID].toString(),
            map[RefundRequestEntity.PARAM_NONCE_STR].toString(),
            map[RefundRequestEntity.PARAM_OP_USER_ID].toString(),
            map[RefundRequestEntity.PARAM_OUT_REFUND_NO].toString(),
            map[RefundRequestEntity.PARAM_OUT_TRADE_NO].toString(),
            map[RefundRequestEntity.PARAM_REFUND_FEE].toString(),
            map[RefundRequestEntity.PARAM_SERVICE].toString(),
            map[RefundRequestEntity.PARAM_SIGN].toString(),
            map[RefundRequestEntity.PARAM_SIGN_TYPE].toString(),
            map[RefundRequestEntity.PARAM_TOTAL_FEE].toString()
        ).enqueue(ResponseCallbackWrapper<RefundQueryResponseEntity>(callback))
    }

}
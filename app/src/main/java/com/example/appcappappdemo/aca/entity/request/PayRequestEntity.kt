package com.example.appcappappdemo.aca.entity.request

import com.example.appcappappdemo.aca.presenter.AppCallAppPresenter

class PayRequestEntity() : BaseRequestEntity() {
    companion object {
        const val PARAM_BODY = "body"
        const val PARAM_DEVICE_INFO = "device_info"
        const val PARAM_MCH_CREATE_IP = "mch_create_ip"
        const val PARAM_MCH_ID = "mch_id"
        const val PARAM_NONCE_STR = "nonce_str"
        const val PARAM_NOTIFY_URL = "notify_url"
        const val PARAM_OUT_TRADE_NO = "out_trade_no"
        const val PARAM_SERVICE = "service"
        const val PARAM_SIGN = "sign"
        const val PARAM_SIGN_TYPE = "sign_type"
        const val PARAM_TOTAL_FEE = "total_fee"
    }


    constructor(
        body: String,
        device_info: String,
        mch_create_ip: String,
        mch_id: String,
        nonce_str: String,
        notify_url: String,
        out_trade_no: String,
        total_fee: String
    ) : this() {

        dataMap[PARAM_BODY] = body
        dataMap[PARAM_DEVICE_INFO] = device_info
        dataMap[PARAM_MCH_CREATE_IP] = mch_create_ip
        dataMap[PARAM_MCH_ID] = mch_id
        dataMap[PARAM_NONCE_STR] = nonce_str
        dataMap[PARAM_NOTIFY_URL] = notify_url
        dataMap[PARAM_OUT_TRADE_NO] = out_trade_no
        dataMap[PARAM_TOTAL_FEE] = total_fee


        dataMap[PARAM_SERVICE] = AppCallAppPresenter.SERVICE_PAY
        dataMap[PARAM_SIGN_TYPE] = AppCallAppPresenter.SIGN_TYPE
        dataMap[PARAM_SIGN] = getSign()
    }

}

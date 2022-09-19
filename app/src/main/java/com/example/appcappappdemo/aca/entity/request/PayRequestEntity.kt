package com.example.appcappappdemo.aca.entity.request

class PayRequestEntity : BaseRequestEntity() {
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
}

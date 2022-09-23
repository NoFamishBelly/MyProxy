package com.example.appcappappdemo.aca.entity.request

class QueryRequestEntity() : BaseRequestEntity() {

    companion object {
        const val PARAM_MCH_ID = "mch_id"
        const val PARAM_NONCE_STR = "nonce_str"
        const val PARAM_OUT_TRADE_NO = "out_trade_no"
        const val PARAM_SERVICE = "service"
        const val PARAM_SIGN = "sign"
        const val PARAM_SIGN_TYPE = "sign_type"


        const val PARAM_VERSION = "version"
        const val PARAM_CHARSET = "charset"
        const val PARAM_TRANSACTION_ID = "transaction_id"
    }


    constructor(
        mch_id: String,
        nonce_str: String,
        out_trade_no: String
    ) : this() {
        dataMap[PARAM_MCH_ID] = mch_id
        dataMap[PARAM_NONCE_STR] = nonce_str
        dataMap[PARAM_OUT_TRADE_NO] = out_trade_no


        dataMap[PARAM_VERSION] = VERSION_2
        dataMap[PARAM_CHARSET] = CHARSET_UTF_8
//        dataMap[PARAM_TRANSACTION_ID] = "null"

        dataMap[PARAM_SERVICE] = SERVICE_QUERY
        dataMap[PARAM_SIGN_TYPE] = SIGN_TYPE_MD5
        dataMap[PARAM_SIGN] = getSign()
    }

}
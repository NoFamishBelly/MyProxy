package com.example.appcappappdemo.aca.entity.request

class RefundQueryRequestEntity() : BaseRequestEntity() {

    companion object {
        const val PARAM_MCH_ID = "mch_id"
        const val PARAM_NONCE_STR = "nonce_str"
        const val PARAM_OP_USER_ID = "op_user_id"
        const val PARAM_OUT_REFUND_NO = "out_refund_no"
        const val PARAM_OUT_TRADE_NO = "out_trade_no"
        const val PARAM_REFUND_FEE = "refund_fee"
        const val PARAM_SERVICE = "service"
        const val PARAM_SIGN = "sign"
        const val PARAM_SIGN_TYPE = "sign_type"
        const val PARAM_TOTAL_FEE = "total_fee"


        const val PARAM_VERSION = "version"
        const val PARAM_CHARSET = "charset"

        /**
         * 商户系统内部的订单号, out_trade_no和transaction_id至少一个必填，
         * 同时存在时transaction_id优先
         */
        const val PARAM_TRANSACTION_ID = "transaction_id"

        /**
         * 平台退款单号，必须和out_trade_refund至少一个必填
         */
        const val PARAM_REFUND_ID = "refund_id"
        const val PARAM_OUT_TRADE_REFUND = "out_trade_refund"
    }


    constructor(
        mch_id: String = "",
        nonce_str: String = "",
        op_user_id: String = "",
        out_refund_no: String = "",
        out_trade_no: String = "",
        refund_fee: String = "",
        total_fee: String = ""
    ) : this() {
        dataMap[PARAM_MCH_ID] = mch_id
        dataMap[PARAM_NONCE_STR] = nonce_str
        dataMap[PARAM_OP_USER_ID] = op_user_id
        dataMap[PARAM_OUT_REFUND_NO] = out_refund_no
        dataMap[PARAM_OUT_TRADE_NO] = out_trade_no
        dataMap[PARAM_REFUND_FEE] = refund_fee
        dataMap[PARAM_TOTAL_FEE] = total_fee


        dataMap[PARAM_VERSION] = VERSION_2
        dataMap[PARAM_CHARSET] = CHARSET_UTF_8

        dataMap[PARAM_SERVICE] = SERVICE_REFUND_QUERY
        dataMap[PARAM_SIGN_TYPE] = SIGN_TYPE_MD5
        dataMap[PARAM_SIGN] = getSign()
    }

}
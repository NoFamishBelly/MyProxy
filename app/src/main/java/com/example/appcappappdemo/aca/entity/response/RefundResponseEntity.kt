package com.example.appcappappdemo.aca.entity.response

import com.example.appcappappdemo.aca.entity.BaseEntity

class RefundResponseEntity(
    var version: String = "",
    var charset: String = "",
    var sign_type: String = "",
    var status: String = "",
    var message: String = "",
    var result_code: String = "",
    var mch_id: String = "",
    var device_info: String = "",
    var nonce_str: String = "",
    var err_code: String = "",
    var err_msg: String = "",
    var sign: String = "",
    var transaction_id: String = "",
    var out_trade_no: String = "",
    var out_refund_no: String = "",
    var refund_id: String = "",
    var refund_channel: String = "",
    var refund_fee: String = "",
    var coupon_refund_fee: String = "",
    var total_fee: String = "",
    var fee_type: String = "",
    var order_fee: String = "",
    var local_total_fee: String = "",
    var local_fee_type: String = "",
    var out_transaction_id: String = "",
    var trade_type: String = ""
) : BaseEntity()
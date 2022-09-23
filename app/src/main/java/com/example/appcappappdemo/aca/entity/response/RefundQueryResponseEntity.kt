package com.example.appcappappdemo.aca.entity.response

import com.example.appcappappdemo.aca.entity.BaseEntity

class RefundQueryResponseEntity(
    var charset: String = "",
    var device_info: String = "",
    var fee_type: String = "",
    var mch_id: String = "",
    var nonce_str: String = "",
    var out_refund_no_0: String = "",
    var out_trade_no: String = "",
    var out_transaction_id: String = "",
    var refund_channel_0: String = "",
    var refund_count: String = "",
    var refund_fee_0: String = "",
    var refund_id_0: String = "",
    var refund_status_0: String = "",
    var refund_time_0: String = "",
    var result_code: String = "",
    var sign: String = "",
    var sign_type: String = "",
    var status: String = "",
    var total_fee: String = "",
    var trade_type: String = "",
    var transaction_id: String = "",
    var version: String = ""
) : BaseEntity()
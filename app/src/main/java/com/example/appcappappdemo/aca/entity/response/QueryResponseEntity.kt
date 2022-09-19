package com.example.appcappappdemo.aca.entity.response

import com.example.appcappappdemo.aca.entity.BaseEntity

class QueryResponseEntity(
    var charset: String = "",
    var device_info: String = "",
    var mch_id: String = "",
    var nonce_str: String = "",
    var result_code: String = "",
    var sign: String = "",
    var sign_type: String = "",
    var status: String = "",
    var trade_state: String = "",
    var version: String = ""
) : BaseEntity()
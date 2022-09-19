package com.example.appcappappdemo.aca.entity.response

import com.example.appcappappdemo.aca.entity.BaseEntity

class RefundResponseEntity(
    var charset: String = "",
    var err_code: String = "",
    var err_msg: String = "",
    var mch_id: String = "",
    var nonce_str: String = "",
    var result_code: String = "",
    var sign: String = "",
    var sign_type: String = "",
    var status: String = "",
    var version: String = ""
) : BaseEntity()
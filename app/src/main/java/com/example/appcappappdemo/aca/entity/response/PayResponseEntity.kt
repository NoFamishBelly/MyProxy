package com.example.appcappappdemo.aca.entity.response

import com.example.appcappappdemo.aca.entity.BaseEntity

class PayResponseEntity(
    var charset: String = "",
    var mch_id: String = "",
    var nonce_str: String = "",
    var result_code: String = "",
    var sign: String = "",
    var sign_type: String = "",
    var status: String = "",
    var thi_response_code: String = "",
    var tn: String = "",
    var version: String = "",
    var message: String = "",
    var device_info: String = "",
    var err_code: String = "",
    var err_msg: String = ""
) : BaseEntity()
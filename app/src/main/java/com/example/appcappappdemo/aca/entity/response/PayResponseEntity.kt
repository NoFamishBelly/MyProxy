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
    var version: String = ""
) : BaseEntity()
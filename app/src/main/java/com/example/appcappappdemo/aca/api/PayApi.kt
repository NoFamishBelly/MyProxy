package com.example.appcappappdemo.aca.api

import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.aca.entity.response.QueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundQueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundResponseEntity
import com.example.appcappappdemo.net.annotation.Address
import com.example.appcappappdemo.net.annotation.Param
import com.example.appcappappdemo.net.listener.request.RequestCall

interface PayApi {

    @Address("http://checkout-switch-dev.wallyt.net/jsonxml/pay/gateway")
    fun pay(
        @Param("body") body: String,
        @Param("device_info") device_info: String,
        @Param("mch_create_ip") mch_create_ip: String,
        @Param("mch_id") mch_id: String,
        @Param("nonce_str") nonce_str: String,
        @Param("notify_url") notify_url: String,
        @Param("out_trade_no") out_trade_no: String,
        @Param("service") service: String,
        @Param("sign") sign: String,
        @Param("sign_type") sign_type: String,
        @Param("total_fee") total_fee: String
    ): RequestCall<PayResponseEntity>


    @Address("http://checkout-switch-dev.wallyt.net/jsonxml/pay/gateway")
    fun query(
        @Param("mch_id") mch_id: String,
        @Param("nonce_str") nonce_str: String,
        @Param("out_trade_no") out_trade_no: String,
        @Param("service") service: String,
        @Param("sign") sign: String,
        @Param("sign_type") sign_type: String
    ): RequestCall<QueryResponseEntity>


    @Address("http://checkout-switch-dev.wallyt.net/jsonxml/pay/gateway")
    fun refund(
        @Param("mch_id") mch_id: String,
        @Param("nonce_str") nonce_str: String,
        @Param("op_user_id") op_user_id: String,
        @Param("out_refund_no") out_refund_no: String,
        @Param("out_trade_no") out_trade_no: String,
        @Param("refund_fee") refund_fee: String,
        @Param("service") service: String,
        @Param("sign") sign: String,
        @Param("sign_type") sign_type: String,
        @Param("total_fee") total_fee: String
    ): RequestCall<RefundResponseEntity>


    @Address("http://checkout-switch-dev.wallyt.net/jsonxml/pay/gateway")
    fun refundQuery(
        @Param("mch_id") mch_id: String,
        @Param("nonce_str") nonce_str: String,
        @Param("op_user_id") op_user_id: String,
        @Param("out_refund_no") out_refund_no: String,
        @Param("out_trade_no") out_trade_no: String,
        @Param("refund_fee") refund_fee: String,
        @Param("service") service: String,
        @Param("sign") sign: String,
        @Param("sign_type") sign_type: String,
        @Param("total_fee") total_fee: String
    ): RequestCall<RefundQueryResponseEntity>


}
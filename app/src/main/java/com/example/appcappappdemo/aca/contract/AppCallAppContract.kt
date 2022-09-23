package com.example.appcappappdemo.aca.contract

import com.example.appcappappdemo.aca.entity.request.PayRequestEntity
import com.example.appcappappdemo.aca.entity.request.QueryRequestEntity
import com.example.appcappappdemo.aca.entity.request.RefundQueryRequestEntity
import com.example.appcappappdemo.aca.entity.request.RefundRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.aca.entity.response.QueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundQueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundResponseEntity
import com.example.appcappappdemo.base.presenter.BasePresenter
import com.example.appcappappdemo.base.view.BaseView

class AppCallAppContract {


    interface View : BaseView<Presenter> {
        fun paySuccess(response: PayResponseEntity)

        fun payFailed(errCode: String, errMsg: String)

        fun querySuccess(response: QueryResponseEntity)

        fun queryFailed(errCode: String, errMsg: String)

        fun refundSuccess(response: RefundResponseEntity)

        fun refundFailed(errCode: String, errMsg: String)

        fun refundQuerySuccess(response: RefundQueryResponseEntity)

        fun refundQueryFailed(errCode: String, errMsg: String)
    }


    interface Presenter : BasePresenter<View> {
        /**
         * 下单
         */
        fun pay(payRequestEntity: PayRequestEntity)

        /**
         * 订单查询
         */
        fun query(queryRequestEntity: QueryRequestEntity)

        /**
         * 退款
         */
        fun refund(refundRequestEntity: RefundRequestEntity)

        /**
         * 退款单查询
         */
        fun refundQuery(refundRequestEntity: RefundQueryRequestEntity)
    }

}
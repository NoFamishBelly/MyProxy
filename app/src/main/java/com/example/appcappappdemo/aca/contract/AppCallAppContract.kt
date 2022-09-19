package com.example.appcappappdemo.aca.contract

import com.example.appcappappdemo.base.presenter.BasePresenter
import com.example.appcappappdemo.base.view.BaseView

class AppCallAppContract {


    interface View : BaseView<Presenter> {
        fun paySuccess(data: String)

        fun payFailed(errCode: String, errMsg: String)
    }


    interface Presenter : BasePresenter<View> {
        fun pay(
            body: String,
            device_info: String,
            mch_create_ip: String,
            mch_id: String,
            nonce_str: String,
            notify_url: String,
            out_trade_no: String,
            total_fee: String,
        )
    }

}
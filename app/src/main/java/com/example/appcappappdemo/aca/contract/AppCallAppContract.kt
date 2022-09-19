package com.example.appcappappdemo.aca.contract

import com.example.appcappappdemo.aca.entity.request.PayRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.base.presenter.BasePresenter
import com.example.appcappappdemo.base.view.BaseView

class AppCallAppContract {


    interface View : BaseView<Presenter> {
        fun paySuccess(data: PayResponseEntity)

        fun payFailed(errCode: String, errMsg: String)
    }


    interface Presenter : BasePresenter<View> {
        fun pay(payRequestEntity: PayRequestEntity)
    }

}
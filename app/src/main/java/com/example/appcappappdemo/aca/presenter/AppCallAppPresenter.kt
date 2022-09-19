package com.example.appcappappdemo.aca.presenter

import com.example.appcappappdemo.aca.contract.AppCallAppContract
import com.example.appcappappdemo.aca.entity.request.PayRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.base.view.BaseView
import com.example.appcappappdemo.net.listener.result.LifecycleMVPResultCallback
import com.example.appcappappdemo.net.manager.AppClient

class AppCallAppPresenter : AppCallAppContract.Presenter {

    companion object {
        const val SERVICE_PAY = "pay.upi.upop.app"
        const val SERVICE_QUERY = "unified.trade.query"
        const val SERVICE_REFUND = "unified.trade.refund"
        const val SERVICE_REFUND_QUERY = "unified.trade.refund"

        const val SIGN_TYPE = "MD5"
    }


    private var mAppCallAppView: AppCallAppContract.View? = null


    override fun pay(payRequestEntity: PayRequestEntity) {
        AppClient.pay(
            payRequestEntity,
            object : LifecycleMVPResultCallback<PayResponseEntity>(mAppCallAppView) {
                override fun onLifecycleMVPSucceed(response: PayResponseEntity) {
                    mAppCallAppView?.let { view ->
                        view.paySuccess(response)
                    }
                }

                override fun onLifecycleMVPFailed(errCode: String, errMsg: String) {
                    mAppCallAppView?.let { view ->
                        view.payFailed(errCode, errMsg)
                    }
                }
            })
    }

    override fun attachView(view: BaseView<*>) {
        mAppCallAppView = view as AppCallAppContract.View
    }

    override fun detachView() {
        mAppCallAppView = null
    }
}
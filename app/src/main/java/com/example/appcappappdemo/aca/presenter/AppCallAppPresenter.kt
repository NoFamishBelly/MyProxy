package com.example.appcappappdemo.aca.presenter

import com.example.appcappappdemo.aca.contract.AppCallAppContract
import com.example.appcappappdemo.aca.entity.request.PayRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.base.view.BaseView
import com.example.appcappappdemo.net.listener.response.ResponseCallback
import com.example.appcappappdemo.net.manager.AppClient
import com.example.appcappappdemo.utils.LogUtils

class AppCallAppPresenter : AppCallAppContract.Presenter {

    companion object {
        const val SERVICE_PAY = "pay.upi.upop.app"
        const val SERVICE_QUERY = "unified.trade.query"
        const val SERVICE_REFUND = "unified.trade.refund"
        const val SERVICE_REFUND_QUERY = "unified.trade.refund"

        const val SIGN_TYPE = "MD5"
    }


    private var mAppCallAppView: AppCallAppContract.View? = null


    override fun pay(
        body: String,
        device_info: String,
        mch_create_ip: String,
        mch_id: String,
        nonce_str: String,
        notify_url: String,
        out_trade_no: String,
        total_fee: String
    ) {
        val payRequestEntity = PayRequestEntity()
        payRequestEntity.dataMap[PayRequestEntity.PARAM_BODY] = body
        payRequestEntity.dataMap[PayRequestEntity.PARAM_DEVICE_INFO] = device_info
        payRequestEntity.dataMap[PayRequestEntity.PARAM_MCH_CREATE_IP] = mch_create_ip
        payRequestEntity.dataMap[PayRequestEntity.PARAM_MCH_ID] = mch_id
        payRequestEntity.dataMap[PayRequestEntity.PARAM_NONCE_STR] = nonce_str
        payRequestEntity.dataMap[PayRequestEntity.PARAM_NOTIFY_URL] = notify_url
        payRequestEntity.dataMap[PayRequestEntity.PARAM_OUT_TRADE_NO] = out_trade_no
        payRequestEntity.dataMap[PayRequestEntity.PARAM_TOTAL_FEE] = total_fee

        payRequestEntity.dataMap[PayRequestEntity.PARAM_SERVICE] = SERVICE_PAY
        payRequestEntity.dataMap[PayRequestEntity.PARAM_SIGN_TYPE] = SIGN_TYPE
        payRequestEntity.dataMap[PayRequestEntity.PARAM_SIGN] = payRequestEntity.getSign()


        LogUtils.i(payRequestEntity.getDataStr())

        AppClient.pay(payRequestEntity.dataMap)
            .enqueue(object : ResponseCallback<PayResponseEntity> {
                override fun onResponseSuccess(response: PayResponseEntity) {
                    mAppCallAppView?.let { view ->
                        view.paySuccess("success")
                    }
                }

                override fun onResponseFail(errCode: String, errMsg: String) {
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
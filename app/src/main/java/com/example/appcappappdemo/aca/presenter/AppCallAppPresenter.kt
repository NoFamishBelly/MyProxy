package com.example.appcappappdemo.aca.presenter

import com.example.appcappappdemo.aca.contract.AppCallAppContract
import com.example.appcappappdemo.aca.entity.request.PayRequestEntity
import com.example.appcappappdemo.aca.entity.request.QueryRequestEntity
import com.example.appcappappdemo.aca.entity.request.RefundRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.aca.entity.response.QueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundResponseEntity
import com.example.appcappappdemo.base.view.BaseView
import com.example.appcappappdemo.net.listener.result.LifecycleMVPResultCallback
import com.example.appcappappdemo.net.manager.AppClient

class AppCallAppPresenter : AppCallAppContract.Presenter {

    private var mAppCallAppView: AppCallAppContract.View? = null


    /**
     * 下单
     */
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


    /**
     * 查询订单
     */
    override fun query(queryRequestEntity: QueryRequestEntity) {
        AppClient.query(
            queryRequestEntity,
            object : LifecycleMVPResultCallback<QueryResponseEntity>(mAppCallAppView) {
                override fun onLifecycleMVPSucceed(response: QueryResponseEntity) {
                    mAppCallAppView?.let {
                        it.querySuccess(response)
                    }
                }

                override fun onLifecycleMVPFailed(errCode: String, errMsg: String) {
                    mAppCallAppView?.let {
                        it.queryFailed(errCode, errMsg)
                    }
                }
            })
    }


    /**
     * 退款
     */
    override fun refund(refundRequestEntity: RefundRequestEntity) {
        AppClient.refund(
            refundRequestEntity,
            object : LifecycleMVPResultCallback<RefundResponseEntity>(mAppCallAppView) {
                override fun onLifecycleMVPSucceed(response: RefundResponseEntity) {
                    mAppCallAppView?.let {
                        it.refundSuccess(response)
                    }
                }

                override fun onLifecycleMVPFailed(errCode: String, errMsg: String) {
                    mAppCallAppView?.let {
                        it.refundFailed(errCode, errMsg)
                    }
                }
            }
        )
    }

    override fun attachView(view: BaseView<*>) {
        mAppCallAppView = view as AppCallAppContract.View
    }

    override fun detachView() {
        mAppCallAppView = null
    }
}
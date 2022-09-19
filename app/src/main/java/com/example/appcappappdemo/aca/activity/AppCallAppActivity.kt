package com.example.appcappappdemo.aca.activity

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.example.appcappappdemo.R
import com.example.appcappappdemo.aca.contract.AppCallAppContract
import com.example.appcappappdemo.aca.presenter.AppCallAppPresenter
import com.example.appcappappdemo.base.activity.BaseAbstractActivity


class AppCallAppActivity : BaseAbstractActivity<AppCallAppContract.Presenter>(),
    AppCallAppContract.View, View.OnClickListener {

    private lateinit var mBtnPay: TextView
    private lateinit var mEtPay: TextView


    private lateinit var mTvDisplay: TextView


    override fun getLayoutId() = R.layout.act_app_call_app

    override fun init() {
        initView()
    }


    private fun initView() {
        mBtnPay = findViewById(R.id.id_btn_pay)
        mEtPay = findViewById(R.id.id_et_pay)
        mTvDisplay = findViewById(R.id.id_tv_display)


        mBtnPay.setOnClickListener(this)
        mEtPay.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        p0?.let { view ->
            when (view.id) {
                R.id.id_btn_pay -> {
                    mPresenter?.let { presenter ->
                        presenter.pay(
                            body = "SRCi\\U652f\\U4ed8",
                            device_info = "000001",
                            mch_create_ip = "127.0.0.1",
                            mch_id = "100510000133",
                            nonce_str = "1663239160",
                            notify_url = "http://172.31.5.43:8888",
                            out_trade_no = getOutTradeNo(),
                            total_fee = "1000"
                        )
                    }
                }
                R.id.id_et_pay -> {

                }
                else -> {

                }
            }
        }
    }


    private fun getOutTradeNo(): String {
        var outTradeNo = "1663239160"

        mEtPay?.let { et ->
            if (!TextUtils.isEmpty(et.text.toString().trim())) {
                outTradeNo = et.text.toString().trim()
            }
        }
        return outTradeNo
    }


    override fun createPresenter() = AppCallAppPresenter()

    override fun paySuccess(data: String) {
        mTvDisplay.text = data
    }

    override fun payFailed(errCode: String, errMsg: String) {
        mTvDisplay.text = "$errCode   $errMsg"
    }
}



package com.example.appcappappdemo.aca.activity

import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import com.example.appcappappdemo.R
import com.example.appcappappdemo.aca.contract.AppCallAppContract
import com.example.appcappappdemo.aca.entity.request.PayRequestEntity
import com.example.appcappappdemo.aca.entity.request.QueryRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.aca.entity.response.QueryResponseEntity
import com.example.appcappappdemo.aca.presenter.AppCallAppPresenter
import com.example.appcappappdemo.base.activity.BaseAbstractActivity
import com.example.appcappappdemo.utils.KotlinUtils


class AppCallAppActivity : BaseAbstractActivity<AppCallAppContract.Presenter>(),
    AppCallAppContract.View, View.OnClickListener {

    private lateinit var mBtnPay: TextView
    private lateinit var mEtPay: TextView

    private lateinit var mBtnQuery: TextView
    private lateinit var mEtQuery: TextView

    private lateinit var mBtnYLPay: TextView
    private lateinit var mBtnRefund: TextView
    private lateinit var mBtnClearLog: TextView

    private lateinit var mBtnRefundQuery: TextView
    private lateinit var mEtRefundQuery: TextView


    private lateinit var mTvDisplay: TextView

    private val mDisplayStringBuilder = StringBuilder("")

    override fun getLayoutId() = R.layout.act_app_call_app

    override fun init() {
        initView()
    }


    private fun initView() {
        mBtnPay = findViewById(R.id.id_btn_pay)
        mEtPay = findViewById(R.id.id_et_pay)

        mBtnQuery = findViewById(R.id.id_btn_query)
        mEtQuery = findViewById(R.id.id_et_query)

        mBtnYLPay = findViewById(R.id.id_btn_yinlian)
        mBtnRefund = findViewById(R.id.id_btn_refund)
        mBtnClearLog = findViewById(R.id.id_btn_clear_log)

        mBtnRefundQuery = findViewById(R.id.id_btn_query_refund)
        mEtRefundQuery = findViewById(R.id.id_et_query_refund)

        mTvDisplay = findViewById(R.id.id_tv_display)
        mTvDisplay.movementMethod = ScrollingMovementMethod.getInstance()


        mBtnPay.setOnClickListener(this)
        mEtPay.setOnClickListener(this)

        mBtnQuery.setOnClickListener(this)
        mEtQuery.setOnClickListener(this)

        mBtnYLPay.setOnClickListener(this)
        mBtnRefund.setOnClickListener(this)
        mBtnClearLog.setOnClickListener(this)

        mBtnRefundQuery.setOnClickListener(this)
        mEtRefundQuery.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        p0?.let { view ->
            when (view.id) {
                R.id.id_btn_pay -> {
                    mPresenter?.let { presenter ->
                        presenter.pay(createPayData())
                    }
                }
                R.id.id_et_pay -> {

                }
                R.id.id_btn_query -> {
                    mPresenter?.let { presenter ->
                        presenter.query(createQueryData())
                    }
                }
                R.id.id_et_query -> {

                }
                R.id.id_btn_yinlian -> {

                }
                R.id.id_btn_refund -> {

                }
                R.id.id_btn_clear_log -> {
                    mDisplayStringBuilder.clear()
                    display()
                }
                R.id.id_btn_query_refund -> {

                }
                R.id.id_et_query_refund -> {

                }
                else -> {

                }
            }
        }
    }


    private fun createPayData(): PayRequestEntity {
        val payRequestEntity = PayRequestEntity(
            body = "SRCi\\U652f\\U4ed8",
            device_info = "000001",
            mch_create_ip = "127.0.0.1",
            mch_id = "100510000133",
            nonce_str = "1663239160",
            notify_url = "http://172.31.5.43:8888",
            out_trade_no = getOutTradeNo(),
            total_fee = "1000"
        )
        mDisplayStringBuilder.append("《======发起下单请求======》\n")
        mDisplayStringBuilder.append("请求参数:\n ${KotlinUtils.getDataJsonStr(payRequestEntity.dataMap)} \n\n")
        display()
        return payRequestEntity
    }


    private fun createQueryData(): QueryRequestEntity {
        val queryRequestEntity = QueryRequestEntity(
            mch_id = "100510000133",
            nonce_str = "1663239198",
            out_trade_no = getOutTradeNo()
        )
        mDisplayStringBuilder.append("《======发起查询请求======》\n")
        mDisplayStringBuilder.append("请求参数:\n ${KotlinUtils.getDataJsonStr(queryRequestEntity.dataMap)} \n\n")
        display()
        return queryRequestEntity
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

    override fun paySuccess(response: PayResponseEntity) {
        showSuccessInfo(response)
    }

    override fun payFailed(errCode: String, errMsg: String) {
        showFailedInfo(errCode, errMsg)
    }

    override fun querySuccess(response: QueryResponseEntity) {
        showSuccessInfo(response)
    }

    override fun queryFailed(errCode: String, errMsg: String) {
        showFailedInfo(errCode, errMsg)
    }


    private fun <T> showSuccessInfo(t: T) {
        val json = KotlinUtils.jsonToString(t)
        mDisplayStringBuilder.append("请求结果(成功): \n$json \n\n\n\n")
        display()
    }

    private fun showFailedInfo(errCode: String, errMsg: String) {
        mDisplayStringBuilder.append("请求结果(失败): \n$errCode  $errMsg \n\n\n\n")
        display()
    }


    private fun display() {
        mTvDisplay.text = mDisplayStringBuilder.toString()
    }
}



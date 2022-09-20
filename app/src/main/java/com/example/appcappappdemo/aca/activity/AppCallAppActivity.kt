package com.example.appcappappdemo.aca.activity

import android.content.Intent
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.example.appcappappdemo.R
import com.example.appcappappdemo.aca.contract.AppCallAppContract
import com.example.appcappappdemo.aca.entity.request.PayRequestEntity
import com.example.appcappappdemo.aca.entity.request.QueryRequestEntity
import com.example.appcappappdemo.aca.entity.request.RefundRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.aca.entity.response.QueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundResponseEntity
import com.example.appcappappdemo.aca.presenter.AppCallAppPresenter
import com.example.appcappappdemo.base.activity.BaseAbstractActivity
import com.example.appcappappdemo.net.manager.AppClient
import com.example.appcappappdemo.utils.KotlinUtils
import com.unionpay.UPPayAssistEx


class AppCallAppActivity : BaseAbstractActivity<AppCallAppContract.Presenter>(),
    AppCallAppContract.View, View.OnClickListener, View.OnTouchListener {

    override fun getLayoutId() = R.layout.act_app_call_app

    companion object {
        const val PAY_SERVER_MODE_UAT = "01"
        const val PAY_RESULT = "pay_result"
        const val PAY_SUCCESS = "success"
        const val PAY_FAIL = "fail"
        const val PAY_CANCEL = "cancel"
    }

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

    private var y1 = 1f


    private var mTn: String = ""
    private val mDisplayStringBuilder = StringBuilder("")

    override fun init() {
        initToolBar()
        initView()
    }


    private fun initToolBar() {
        setToolBarTitle(getString(R.string.string_tool_bar))

        val rightText = TextView(this)
        rightText.text = getString(R.string.string_tool_bar_param)

        val toolBarRightView = getToolBarRightLayout()
        toolBarRightView?.let {
            it.addView(rightText)
        }
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
        mTvDisplay.setOnTouchListener(this)


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


    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        p1?.let { event ->
            p0?.let { v ->
                if (event.action === MotionEvent.ACTION_DOWN) {
                    //按下去第一个Y坐标
                    y1 = event.y
                    //通知父控件不要干扰
                    v.parent.requestDisallowInterceptTouchEvent(true)
                }
                if (event.action === MotionEvent.ACTION_MOVE) {
                    if (y1 - event.y > 50) {
                        v.parent
                            .requestDisallowInterceptTouchEvent(
                                canVerticalScroll(
                                    v as TextView,
                                    true
                                )
                            )
                    } else if (event.y - y1 > 380) {
                        v.parent
                            .requestDisallowInterceptTouchEvent(
                                canVerticalScroll(
                                    v as TextView,
                                    false
                                )
                            )
                    }
                }
                if (event.action === MotionEvent.ACTION_UP) {
                    v.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
        }
        return false
    }


    /**
     * @param view 滑动的TextView
     * @param flag true 向上滑动 false 向下滑动
     * @return
     */
    private fun canVerticalScroll(view: TextView, flag: Boolean): Boolean {
        //滚动的距离
        val scrollY = view.scrollY
        //控件内容的总高度
        val scrollRange = view.layout.height
        //控件实际显示的高度
        val scrollExtent = view.height - view.compoundPaddingTop - view.compoundPaddingBottom
        //控件内容总高度与实际显示高度的差值
        val scrollDifference = scrollRange - scrollExtent
        return if (flag) {
            scrollDifference != scrollY
        } else {
            scrollDifference == 0
        }
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
                    upPay()
                }
                R.id.id_btn_refund -> {
                    mPresenter?.let { presenter ->
                        presenter.refund(createRefundData())
                    }
                }
                R.id.id_btn_clear_log -> {
                    mDisplayStringBuilder.clear()
                    display()
                }
                R.id.id_btn_query_refund -> {
                    mPresenter?.let { presenter ->
                        presenter.refund(createRefundQueryData())
                    }
                }
                R.id.id_et_query_refund -> {

                }
                else -> {

                }
            }
        }
    }


    /**
     * 银联支付
     */
    private fun upPay() {
        var tn = mTn
        if (TextUtils.isEmpty(tn)) {
            tn = getTn()
        }
        mDisplayStringBuilder.append("《======银联支付======》\n交易订单号tn : $tn\n")
        UPPayAssistEx.startPay(getActivity(), null, null, tn, PAY_SERVER_MODE_UAT)
        display()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let { result ->
            val msg = result.getStringExtra(PAY_RESULT)
            if (msg.equals(PAY_SUCCESS, ignoreCase = true)) {
                //支付成功
                mDisplayStringBuilder.append("支付成功\n\n")
            } else if (msg.equals(PAY_FAIL, ignoreCase = true)) {
                //支付失败
                mDisplayStringBuilder.append("支付失败\n\n")
            } else if (msg.equals(PAY_CANCEL, ignoreCase = true)) {
                //支付取消
                mDisplayStringBuilder.append("支付取消\n\n")
            } else {

            }
            display()
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
        mDisplayStringBuilder.append("url: ${AppClient.getBaseUrl()}\n")
        showRequestInfo(KotlinUtils.getDataJsonStr(payRequestEntity.dataMap))
        return payRequestEntity
    }


    private fun createQueryData(): QueryRequestEntity {
        val queryRequestEntity = QueryRequestEntity(
            mch_id = "100510000133",
            nonce_str = "1663239198",
            out_trade_no = getOutTradeNo()
        )
        mDisplayStringBuilder.append("《======发起查询请求======》\n")
        mDisplayStringBuilder.append("url: ${AppClient.getBaseUrl()}\n")
        showRequestInfo(KotlinUtils.getDataJsonStr(queryRequestEntity.dataMap))
        return queryRequestEntity
    }

    private fun createRefundData(): RefundRequestEntity {
        val refundRequestEntity = RefundRequestEntity(
            mch_id = "100510000133",
            nonce_str = "1663239227",
            op_user_id = "100510000133",
            out_refund_no = getRefundOutTradeNo(),
            out_trade_no = getOutTradeNo(),
            refund_fee = "100",
            total_fee = "1000"
        )
        mDisplayStringBuilder.append("《======发起退款请求======》\n")
        mDisplayStringBuilder.append("url: ${AppClient.getBaseUrl()}\n")
        showRequestInfo(KotlinUtils.getDataJsonStr(refundRequestEntity.dataMap))
        return refundRequestEntity
    }

    private fun createRefundQueryData(): RefundRequestEntity {
        val refundRequestEntity = RefundRequestEntity(
            mch_id = "100510000133",
            nonce_str = "1663239227",
            op_user_id = "100510000133",
            out_refund_no = getRefundOutTradeNo(),
            out_trade_no = getOutTradeNo(),
            refund_fee = "100",
            total_fee = "1000"
        )
        mDisplayStringBuilder.append("《======发起退款查询请求======》\n")
        mDisplayStringBuilder.append("url: ${AppClient.getBaseUrl()}\n")
        showRequestInfo(KotlinUtils.getDataJsonStr(refundRequestEntity.dataMap))
        return refundRequestEntity
    }

    /**
     * 获取tn号
     */
    private fun getTn(): String {
        var tn = "637868762282541849211"
        mEtPay?.let { et ->
            if (!TextUtils.isEmpty(et.text.toString().trim())) {
                tn = et.text.toString().trim()
            }
        }
        return tn
    }


    /**
     * 获取订单号
     */
    private fun getOutTradeNo(): String {
        var outTradeNo = "1663239160"
        mEtQuery?.let { et ->
            if (!TextUtils.isEmpty(et.text.toString().trim())) {
                outTradeNo = et.text.toString().trim()
            }
        }
        return outTradeNo
    }

    /**
     * 获取退款订单号
     */
    private fun getRefundOutTradeNo(): String {
        var outTradeNo = "1663239227"
        mEtRefundQuery?.let { et ->
            if (!TextUtils.isEmpty(et.text.toString().trim())) {
                outTradeNo = et.text.toString().trim()
            }
        }
        return outTradeNo
    }


    override fun createPresenter() = AppCallAppPresenter()

    override fun paySuccess(response: PayResponseEntity) {
        mTn = response.tn
        if (!TextUtils.isEmpty(response.tn)) {
            mEtPay.text = response.tn
        }
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


    override fun refundSuccess(response: RefundResponseEntity) {
        showSuccessInfo(response)
    }

    override fun refundFailed(errCode: String, errMsg: String) {
        showFailedInfo(errCode, errMsg)
    }


    private fun showRequestInfo(json: String?) {
        val data = json!!.substring(1, json.length - 1)
        mDisplayStringBuilder.append("请求参数: \n")
        mDisplayStringBuilder.append("{\n")
        val array = data.split(",")
        for (str in array) {
            mDisplayStringBuilder.append("\t$str\n")
        }
        mDisplayStringBuilder.append("}\n\n")
        display()
    }


    private fun <T> showSuccessInfo(t: T) {
        val json = KotlinUtils.jsonToString(t)

        val data = json!!.substring(1, json.length - 1)
        mDisplayStringBuilder.append("请求结果(成功): \n")
        mDisplayStringBuilder.append("{\n")
        val array = data.split(",")
        for (str in array) {
            mDisplayStringBuilder.append("\t$str\n")
        }
        mDisplayStringBuilder.append("}\n\n\n\n")
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



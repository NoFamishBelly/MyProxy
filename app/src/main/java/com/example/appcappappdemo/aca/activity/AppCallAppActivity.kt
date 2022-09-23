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
import com.example.appcappappdemo.aca.entity.request.RefundQueryRequestEntity
import com.example.appcappappdemo.aca.entity.request.RefundRequestEntity
import com.example.appcappappdemo.aca.entity.response.PayResponseEntity
import com.example.appcappappdemo.aca.entity.response.QueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundQueryResponseEntity
import com.example.appcappappdemo.aca.entity.response.RefundResponseEntity
import com.example.appcappappdemo.aca.presenter.AppCallAppPresenter
import com.example.appcappappdemo.base.activity.BaseAbstractActivity
import com.example.appcappappdemo.utils.Constants
import com.example.appcappappdemo.utils.KotlinUtils
import com.example.appcappappdemo.utils.ParamUtils
import com.unionpay.UPPayAssistEx


class AppCallAppActivity : BaseAbstractActivity<AppCallAppContract.Presenter>(),
    AppCallAppContract.View, View.OnClickListener, View.OnTouchListener {

    override fun getLayoutId() = R.layout.act_app_call_app

    companion object {
        /**
         * PAY_RESULT      银联支付结果
         * PAY_SUCCESS    银联支付成功
         * PAY_FAIL           银联支付失败
         * PAY_CANCEL      银联支付取消
         */
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

    private var mTimeStamp: String = getTimeStamp()


    private var mTn: String = ""
    private val mDisplayStringBuilder = StringBuilder("")

    override fun init() {
        initToolBar()
        initView()
    }


    /**
     * 初始化ToolBar
     */
    private fun initToolBar() {
        setToolBarTitle(getString(R.string.string_tool_bar))

        val rightText = TextView(this)
        rightText.text = getString(R.string.string_tool_bar_param)

        rightText.setOnClickListener {
            startActivityForResult(
                Intent(getActivity(), ParamSettingActivity::class.java),
                Constants.ACTIVITY_REQUEST_CODE_PARAM_SET
            )
        }

        val toolBarRightView = getToolBarRightLayout()
        toolBarRightView?.let {
            it.addView(rightText)
        }
    }


    /**
     * 初始化 UI 界面
     */
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


    /**
     * 解决 ScrollView与TextView滑动冲突
     */
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
                        v.parent.requestDisallowInterceptTouchEvent(
                            canVerticalScroll(
                                v as TextView, true
                            )
                        )
                    } else if (event.y - y1 > 380) {
                        v.parent.requestDisallowInterceptTouchEvent(
                            canVerticalScroll(
                                v as TextView, false
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
     * 解决 ScrollView与TextView滑动冲突
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


    /**
     * 按钮点击事件
     */
    override fun onClick(p0: View?) {
        p0?.let { view ->
            when (view.id) {
                R.id.id_btn_pay -> {
                    //下单
                    mPresenter?.let { presenter ->
                        presenter.pay(createPayData())
                    }
                }
                R.id.id_et_pay -> {

                }
                R.id.id_btn_query -> {
                    //查询订单
                    mPresenter?.let { presenter ->
                        presenter.query(createQueryData())
                    }
                }
                R.id.id_et_query -> {

                }
                R.id.id_btn_yinlian -> {
                    //银联支付
                    upPay()
                }
                R.id.id_btn_refund -> {
                    //退款
                    mPresenter?.let { presenter ->
                        presenter.refund(createRefundData())
                    }
                }
                R.id.id_btn_clear_log -> {
                    //清空日志
                    mDisplayStringBuilder.clear()
                    display()
                }
                R.id.id_btn_query_refund -> {
                    //退款单查询
                    mPresenter?.let { presenter ->
                        presenter.refundQuery(createRefundQueryData())
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
//        if (UPPayAssistEx.checkWalletInstalled(getActivity())) {
//            //当判断用户手机上已安装银联Apk，商户客户端可以做相应个性化处理
//            var tn = mTn
//            if (TextUtils.isEmpty(tn)) {
//                tn = getTn()
//            }
//            mDisplayStringBuilder.append("《======银联支付======》\n交易订单号tn : $tn\n")
//            UPPayAssistEx.startPay(getActivity(), null, null, tn, ParamUtils.getYinLianFromSp())
//            display()
//        } else {
//            mDisplayStringBuilder.append(getString(R.string.string_uninstall_yin_lian))
//        }

        var tn = mTn
        if (TextUtils.isEmpty(tn)) {
            tn = getTn()
        }
        mDisplayStringBuilder.append("《======银联支付======》\n交易订单号tn : $tn\n")
        UPPayAssistEx.startPay(getActivity(), null, null, tn, ParamUtils.getYinLianFromSp())
        display()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.ACTIVITY_REQUEST_CODE_PARAM_SET && resultCode == Constants.ACTIVITY_RESULT_CODE_PARAM_SET) {
            //修改配置参数
            displayToast(getString(R.string.string_modify_success))
        } else {
            //银联支付结果
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
    }


    /**
     * 获取时间戳 作测试out_trade_no
     */
    private fun getTimeStamp(): String {
        val timeStamp = System.currentTimeMillis().toString()
        return timeStamp.substring(0, timeStamp.length - 3)
    }


    /**
     * 下单  接口请求参数
     */
    private fun createPayData(): PayRequestEntity {
        mTimeStamp = getTimeStamp()
        mEtQuery.text = mTimeStamp
        val payRequestEntity = PayRequestEntity(
            body = "SRCi\\U652f\\U4ed8",
            device_info = "000001",
            mch_create_ip = "127.0.0.1",
            mch_id = ParamUtils.getMchIdFromSp(),
            nonce_str = getOutTradeNo(),
            notify_url = "http://172.31.5.43:8888",
            out_trade_no = getOutTradeNo(),
            total_fee = ParamUtils.getPayMoneyFromSp()
        )
        mDisplayStringBuilder.append("《======发起下单请求======》\n")
        mDisplayStringBuilder.append("url: ${ParamUtils.getBaseUrlFromSp()}\n")
        showRequestInfo(KotlinUtils.getDataJsonStr(payRequestEntity.dataMap))
        return payRequestEntity
    }


    /**
     * 查询订单  接口请求参数
     */
    private fun createQueryData(): QueryRequestEntity {
        val queryRequestEntity = QueryRequestEntity(
            mch_id = ParamUtils.getMchIdFromSp(),
            nonce_str = getOutTradeNo(),
            out_trade_no = getOutTradeNo()
        )
        mDisplayStringBuilder.append("《======发起查询请求======》\n")
        mDisplayStringBuilder.append("url: ${ParamUtils.getBaseUrlFromSp()}\n")
        showRequestInfo(KotlinUtils.getDataJsonStr(queryRequestEntity.dataMap))
        return queryRequestEntity
    }


    /**
     * 退款  接口请求参数
     */
    private fun createRefundData(): RefundRequestEntity {
        val refundRequestEntity = RefundRequestEntity(
            mch_id = ParamUtils.getMchIdFromSp(),
            nonce_str = getOutTradeNo(),
            op_user_id = "100510000133",
            out_refund_no = getRefundOutTradeNo(),
            out_trade_no = getOutTradeNo(),
            refund_fee = ParamUtils.getRefundMoneyFromSp(),
            total_fee = ParamUtils.getPayMoneyFromSp()
        )
        mDisplayStringBuilder.append("《======发起退款请求======》\n")
        mDisplayStringBuilder.append("url: ${ParamUtils.getBaseUrlFromSp()}\n")
        showRequestInfo(KotlinUtils.getDataJsonStr(refundRequestEntity.dataMap))
        return refundRequestEntity
    }


    /**
     * 退款单查询  接口请求参数
     */
    private fun createRefundQueryData(): RefundQueryRequestEntity {
        val refundRequestEntity = RefundQueryRequestEntity(
            mch_id = ParamUtils.getMchIdFromSp(),
            nonce_str = getOutTradeNo(),
            op_user_id = "100510000133",
            out_refund_no = getRefundOutTradeNo(),
            out_trade_no = getOutTradeNo(),
            refund_fee = ParamUtils.getRefundMoneyFromSp(),
            total_fee = ParamUtils.getPayMoneyFromSp()
        )
        mDisplayStringBuilder.append("《======发起退款查询请求======》\n")
        mDisplayStringBuilder.append("url: ${ParamUtils.getBaseUrlFromSp()}\n")
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
        var outTradeNo = mTimeStamp
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
        var outRefundTradeNo = mTimeStamp
        mEtRefundQuery?.let { et ->
            if (!TextUtils.isEmpty(et.text.toString().trim())) {
                outRefundTradeNo = et.text.toString().trim()
            }
        }
        return outRefundTradeNo
    }


    override fun createPresenter() = AppCallAppPresenter()


    /**
     * 下单成功
     */
    override fun paySuccess(response: PayResponseEntity) {
        mTn = response.tn
        if (!TextUtils.isEmpty(response.tn)) {
            mEtPay.text = response.tn
        }
        showResponseSuccessInfo(response)
    }


    /**
     * 下单失败
     */
    override fun payFailed(errCode: String, errMsg: String) {
        showResponseFailedInfo(errCode, errMsg)
    }


    /**
     * 查询订单成功
     */
    override fun querySuccess(response: QueryResponseEntity) {
        showResponseSuccessInfo(response)
    }


    /**
     * 查询订单失败
     */
    override fun queryFailed(errCode: String, errMsg: String) {
        showResponseFailedInfo(errCode, errMsg)
    }


    /**
     * 退款成功
     */
    override fun refundSuccess(response: RefundResponseEntity) {
//        mEtRefundQuery.text = response.
        showResponseSuccessInfo(response)
    }

    /**
     * 退款失败
     */
    override fun refundFailed(errCode: String, errMsg: String) {
        showResponseFailedInfo(errCode, errMsg)
    }


    /**
     * 退款单查询成功
     */
    override fun refundQuerySuccess(response: RefundQueryResponseEntity) {
        showResponseSuccessInfo(response)
    }

    /**
     * 退款单查询失败
     */
    override fun refundQueryFailed(errCode: String, errMsg: String) {
        showResponseFailedInfo(errCode, errMsg)
    }


    /**
     * 展示请求参数信息
     */
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


    /**
     * 展示 接口返回成功信息
     */
    private fun <T> showResponseSuccessInfo(t: T) {
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


    /**
     * 展示 接口返回错误信息
     */
    private fun showResponseFailedInfo(errCode: String, errMsg: String) {
        mDisplayStringBuilder.append("请求结果(失败): \n$errCode  $errMsg \n\n\n\n")
        display()
    }


    /**
     * 展示信息
     */
    private fun display() {
        mTvDisplay.text = mDisplayStringBuilder.toString()
    }
}



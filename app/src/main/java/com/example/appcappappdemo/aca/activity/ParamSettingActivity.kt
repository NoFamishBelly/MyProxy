package com.example.appcappappdemo.aca.activity

import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import com.example.appcappappdemo.R
import com.example.appcappappdemo.base.activity.BaseAbstractActivity
import com.example.appcappappdemo.base.presenter.BasePresenter
import com.example.appcappappdemo.utils.Constants
import com.example.appcappappdemo.utils.ParamUtils

class ParamSettingActivity : BaseAbstractActivity<BasePresenter<*>>() {

    override fun getLayoutId() = R.layout.act_param_setting


    private lateinit var mEtPayMoney: EditText
    private lateinit var mEtRefundMoney: EditText
    private lateinit var mEtMchId: EditText
    private lateinit var mEtPrivateKey: EditText
    private lateinit var mEtYinLian: EditText
    private lateinit var mEtBaseUrl: EditText


    override fun init() {
        initToolBar()
        initView()
    }


    private fun initToolBar() {
        setToolBarTitle(getString(R.string.string_tool_bar_param))
        val rightText = TextView(this)
        rightText.text = getString(R.string.string_tool_bar_save)

        rightText.setOnClickListener {
            saveData()
            setResult(Constants.ACTIVITY_RESULT_CODE_PARAM_SET)
            finish()
        }

        val toolBarRightView = getToolBarRightLayout()
        toolBarRightView?.let {
            it.addView(rightText)
        }
    }


    private fun saveData() {
        val payMoney = mEtPayMoney.text.toString().trim()
        val refundMoney = mEtRefundMoney.text.toString().trim()
        val mchId = mEtMchId.text.toString().trim()
        val privateKey = mEtPrivateKey.text.toString().trim()
        val yinLian = mEtYinLian.text.toString().trim()
        val baseUrl = mEtBaseUrl.text.toString().trim()


        if (isTextNotEmpty(payMoney)) {
            ParamUtils.putPayMoneyToSp(payMoney)
        }

        if (isTextNotEmpty(refundMoney)) {
            ParamUtils.putRefundMoneyToSp(refundMoney)
        }

        if (isTextNotEmpty(mchId)) {
            ParamUtils.putMchIdToSp(mchId)
        }

        if (isTextNotEmpty(privateKey)) {
            ParamUtils.putPrivateKeyToSp(privateKey)
        }

        if (isTextNotEmpty(yinLian)) {
            ParamUtils.putYinLianToSp(yinLian)
        }

        if (isTextNotEmpty(baseUrl)) {
            ParamUtils.putBaseUrlToSp(baseUrl)
        }
    }


    private fun isTextNotEmpty(text: String?) = !TextUtils.isEmpty(text)


    private fun initView() {
        mEtPayMoney = findViewById(R.id.id_et_pay_money)
        mEtRefundMoney = findViewById(R.id.id_et_refund_money)
        mEtMchId = findViewById(R.id.id_et_mch_id)
        mEtPrivateKey = findViewById(R.id.id_et_private_key)
        mEtYinLian = findViewById(R.id.id_et_yin_lian)
        mEtBaseUrl = findViewById(R.id.id_et_base_url)


        mEtPayMoney.setText(ParamUtils.getPayMoneyFromSp())
        mEtRefundMoney.setText(ParamUtils.getRefundMoneyFromSp())
        mEtMchId.setText(ParamUtils.getMchIdFromSp())
        mEtPrivateKey.setText(ParamUtils.getPrivateKeyFromSp())
        mEtYinLian.setText(ParamUtils.getYinLianFromSp())
        mEtBaseUrl.setText(ParamUtils.getBaseUrlFromSp())
    }


    override fun createPresenter(): Nothing? = null
}
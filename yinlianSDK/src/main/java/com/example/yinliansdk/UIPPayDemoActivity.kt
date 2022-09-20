package com.example.yinliansdk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.unionpay.UPPayAssistEx

class UIPPayDemoActivity : AppCompatActivity(), View.OnClickListener {


    companion object {
        const val PAY_SERVER_MODE_UAT = "01"
        const val PAY_RESULT = "pay_result"
        const val PAY_SUCCESS = "success"
        const val PAY_FAIL = "fail"
        const val PAY_CANCEL = "cancel"
        const val PAY_RESULT_DATA = "result_data"
        const val PAY_SIGN = "sign"
        const val PAY_DATA = "data"
    }


    private lateinit var mBtnPay: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_uippay_demo)
        initView()

    }


    private fun initView() {
        mBtnPay = findViewById(R.id.id_btn_pay)
        mBtnPay.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        p0?.let { view ->
            when (view.id) {
                R.id.id_btn_pay -> {
                    pay()
                }
                else -> {

                }
            }
        }
    }


    private fun pay() {
        UPPayAssistEx.startPay(this, null, null, "121374298", PAY_SERVER_MODE_UAT)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val mDisplayStringBuilder = StringBuilder("")
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
            Log.i("TAG_ZLZ", "===> $mDisplayStringBuilder")
        }
    }

}
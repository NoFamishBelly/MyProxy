package com.example.yinliansdk

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.unionpay.UPPayAssistEx

class UIPPayDemoActivity : AppCompatActivity(), View.OnClickListener {


    companion object {
        const val SERVER_MODE_UAT = "01"
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
        UPPayAssistEx.startPay(this, null, null, "tn", SERVER_MODE_UAT)
    }

}
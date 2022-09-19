package com.example.appcappappdemo

import com.example.appcappappdemo.base.activity.BaseAbstractActivity
import com.example.appcappappdemo.base.presenter.BasePresenter
import com.example.appcappappdemo.base.view.BaseView

class MainActivity : BaseAbstractActivity<BasePresenter<BaseView<*>>>() {

    override fun getLayoutId() = R.layout.activity_main

    override fun init() {

    }

    override fun createPresenter(): BasePresenter<BaseView<*>>? {
        return null
    }
}
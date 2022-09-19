package com.example.appcappappdemo.base.presenter

import com.example.appcappappdemo.base.view.BaseView

/**
 * @author lizheng.zhao
 * @date 2022/09/16
 */
interface BasePresenter<V : BaseView<*>> {

    /**
     * Presenter绑定View，一般在activity的onCreated()会调用
     */
    fun attachView(view: BaseView<*>)

    /**
     * Presenter解绑View，一般在activity的onDestroy()会调用
     */
    fun detachView()
}
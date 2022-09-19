package com.example.appcappappdemo.base.view


/**
 * @author lizheng.zhao
 * @date 2022/09/16
 */
interface BaseView<P> {

    /**
     * 显示Loading对话框，并且Loading是允许取消的
     * true : 展示loading     false : 隐藏loading
     */
    fun setEnableLoading(isEnable: Boolean)


    /**
     * 判断是否已经attach到presenter
     */
    fun isAttachedToPresenter(): Boolean
}
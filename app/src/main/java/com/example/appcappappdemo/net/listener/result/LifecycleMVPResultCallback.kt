package com.example.appcappappdemo.net.listener.result

import com.example.appcappappdemo.base.view.BaseView
import java.lang.ref.WeakReference

/**
 * @author lizheng.zhao
 * @date 2022/09/16
 */
abstract class LifecycleMVPResultCallback<T> : ResultCallback<T> {

    /**
     * mLifecycleView
     * mIsEnableLoading                           自动启动和关闭加载对话框
     * mIsAutoDismissLoadingInSuccessful   是否再返回成功的时候关闭加载对话框
     */
    private lateinit var mLifecycleView: WeakReference<BaseView<*>>
    private var mIsEnableLoading: Boolean = true
    private var mIsAutoDismissLoadingInSuccessful: Boolean = true

    constructor(
        lifecycleView: BaseView<T>?,
        isEnableLoading: Boolean = true,
        isAutoDismissLoadingInSuccessful: Boolean = true
    ) {
        lifecycleView?.let { view ->
            mLifecycleView = WeakReference(view)
            mIsEnableLoading = isEnableLoading
            mIsAutoDismissLoadingInSuccessful = isAutoDismissLoadingInSuccessful
            if (mIsEnableLoading) {
                //展示loading
                view.setEnableLoading(true)
            }
        }
    }


    override fun onSuccess(response: T) {
        val view = mLifecycleView.get() as BaseView<*>
        view?.let {
            if (it.isAttachedToPresenter()) {
                if (mIsEnableLoading && mIsAutoDismissLoadingInSuccessful) {
                    it.setEnableLoading(false)
                }
                onLifecycleMVPSucceed(response)
                mLifecycleView.clear()
            }
        }
    }


    override fun onFail(errCode: String, errMsg: String) {
        val view = mLifecycleView.get() as BaseView<*>
        view?.let {
            if (mIsEnableLoading && mIsAutoDismissLoadingInSuccessful) {
                it.setEnableLoading(false)
            }
            onLifecycleMVPFailed(errCode, errMsg)
            mLifecycleView.clear()
        }
    }


    protected abstract fun onLifecycleMVPSucceed(response: T)

    protected abstract fun onLifecycleMVPFailed(errCode: String, errMsg: String)

}
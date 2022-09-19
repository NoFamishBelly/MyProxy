package com.example.appcappappdemo.net.listener.response

import com.example.appcappappdemo.net.listener.result.ResultCallback


/**
 * @author lizheng.zhao
 * @date 2022/09/16
 * @description 这个类是ResponseCallback的包装，主要做了对某些错误码的处理，比如登录过期，没有网络等等
 */
open class ResponseCallbackWrapper<T> : ResponseCallback<T> {

    companion object {
        const val TAG = "ResponseCallbackWrapper"
        const val CODE_SUCCESS = "200"
        const val CODE_NETWORK_TIMEOUT = "NetworkTimeout"
    }


    protected var mCallback: ResultCallback<T>? = null


    constructor(callback: ResultCallback<T>?) {
        mCallback = callback
    }


    override fun onResponseFail(errCode: String, errMsg: String) {
        mCallback?.let { callback ->
            callback.onResultFail(errCode, errMsg)
        }
    }


    override fun onResponseSuccess(response: T) {
        mCallback?.let { callback ->
            callback.onResultSuccess(response)
        }
    }
}
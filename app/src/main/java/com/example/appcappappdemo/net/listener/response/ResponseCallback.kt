package com.example.appcappappdemo.net.listener.response


/**
 * @author lizheng.zhao
 * @date 2022/09/16
 */
interface ResponseCallback<T> {
    fun onResponseSuccess(response: T)
    fun onResponseFail(errCode: String, errMsg: String)
}
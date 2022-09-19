package com.example.appcappappdemo.net.listener.result

/**
 * @author lizheng.zhao
 * @date 2022/09/16
 */
interface ResultCallback<T> {

    fun onSuccess(response: T)

    fun onFail(errCode: String, errMsg: String)

}
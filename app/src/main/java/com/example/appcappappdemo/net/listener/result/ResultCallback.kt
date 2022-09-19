package com.example.appcappappdemo.net.listener.result

/**
 * @author lizheng.zhao
 * @date 2022/09/16
 */
interface ResultCallback<T> {

    fun onResultSuccess(response: T)

    fun onResultFail(errCode: String, errMsg: String)

}
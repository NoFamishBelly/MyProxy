package com.example.appcappappdemo.net.listener.result

/**
 * @author lizheng.zhao
 * @date 2022/09/16
 * @description 接口返回结果转换为界面交互 - 基类
 */
interface ResultCallback<T> {

    fun onResultSuccess(response: T)

    fun onResultFail(errCode: String, errMsg: String)

}
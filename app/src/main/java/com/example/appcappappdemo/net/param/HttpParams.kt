package com.example.appcappappdemo.net.param


/**
 * @author lizheng.zhao
 * @date 2022/09/16
 */
class HttpParams {
    var mParams: HashMap<String, String>
    var mUrl: String? = null

    constructor() {
        mUrl = ""
        mParams = HashMap()
    }

    constructor(url: String, params: HashMap<String, String>) {
        mUrl = url
        mParams = params
    }

}
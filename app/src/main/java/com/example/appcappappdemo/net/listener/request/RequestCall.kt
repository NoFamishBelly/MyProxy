package com.example.appcappappdemo.net.listener.request

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.example.appcappappdemo.BuildConfig
import com.example.appcappappdemo.net.listener.response.ResponseCallback
import com.example.appcappappdemo.net.manager.OkHttpManager
import com.example.appcappappdemo.net.param.HttpParams
import com.example.appcappappdemo.utils.KotlinUtils
import com.example.appcappappdemo.utils.LogUtils
import com.example.appcappappdemo.utils.ParamUtils
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type


/**
 * @author lizheng.zhao
 * @date 2022/09/16
 * @description 接口发出请求
 */
class RequestCall<T> {

    companion object {
        const val CODE_SUCCESS = "200"
        const val CODE_NETWORK_TIMEOUT = "NetworkTimeout"
        const val CODE_JSON_EXCEPTION = "JsonException"

        const val CODE_NO_RESULT = "no result"
    }

    /**
     * mHttpParams   请求参数
     * mGenericType  返回值的泛型类型
     * mMainHandler  回调都是在主线程
     */
    private var mHttpParams: HttpParams? = null
    private var mResponseDataType: Type
    private val mMainHandler = Handler(Looper.getMainLooper())


    constructor(
        httpParams: HttpParams,
        responseDataType: Type
    ) {
        mHttpParams = httpParams
        mResponseDataType = responseDataType
    }


    fun enqueue(callback: ResponseCallback<T>) {
        enqueue(buildCall(), mResponseDataType, callback)
    }


    private fun enqueue(
        call: Call,
        genericType: Type,
        callback: ResponseCallback<T>
    ) {
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                callFail(CODE_NO_RESULT, "okHttp连接失败===》${e!!.toString()}", callback)
            }

            override fun onResponse(call: Call?, response: Response?) {
                response?.let { result ->
                    if (result.isSuccessful) {
                        val inputStream = response.body().byteStream()
                        val reader = BufferedReader(InputStreamReader(inputStream))
                        val stringBuilder = StringBuilder()
                        do {
                            var line = reader.readLine()
                            if (!TextUtils.isEmpty(line)) {
                                stringBuilder.append(line)
                            }
                        } while (!TextUtils.isEmpty(line))

                        val json = stringBuilder.toString()
                        LogUtils.i("返回参数 ===》 $json")

                        val entity = KotlinUtils.stringToJson<T>(
                            json,
                            genericType
                        )

                        entity?.let {
                            callSuccess(entity, callback)
                            return@onResponse
                        }
                        callFail(CODE_JSON_EXCEPTION, "json解析异常", callback)
                    } else {
                        callFail(result.code().toString(), "有返回，okhttp返回错误码", callback)
                    }
                }
            }
        })
    }


    private fun buildCall(): Call {
        val builder = Request.Builder()
        mHttpParams?.let { param ->
            val url =
                if (BuildConfig.isBaseUrlEditable) ParamUtils.getBaseUrlFromSp()
                else param.mUrl
            val json = KotlinUtils.getDataJsonStr(param.mParams)
            builder.url(url)
            builder.post(
                RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    json
                )
            )
            LogUtils.i("请求参数 ===》 $json")
        }
        return OkHttpManager.getOkHttpClient().newCall(builder.build())
    }


    private fun callFail(errCode: String, errMsg: String, callback: ResponseCallback<T>) {
        mMainHandler.post {
            callback.onResponseFail(errCode, errMsg)
        }
    }

    private fun callSuccess(response: T, callback: ResponseCallback<T>) {
        mMainHandler.post {
            callback.onResponseSuccess(response)
        }
    }

}
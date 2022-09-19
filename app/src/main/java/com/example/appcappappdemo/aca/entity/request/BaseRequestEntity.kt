package com.example.appcappappdemo.aca.entity.request

import com.example.appcappappdemo.BuildConfig
import com.example.appcappappdemo.aca.entity.BaseEntity
import com.example.appcappappdemo.utils.MD5Utils
import java.util.*

open class BaseRequestEntity(val dataMap: HashMap<String, String> = HashMap()) : BaseEntity() {

    fun getSign(): String {
        if (dataMap.isNotEmpty()) {
            val array = dataMap.keys.toTypedArray()
            Arrays.sort(array)
            val str = StringBuilder()
            for (k in array) {
                str.append("$k=${dataMap[k]}&")
            }
            str.append("key=${BuildConfig.privateKey}")
            val md5Str = MD5Utils.md5s(str.toString())
            return md5Str.uppercase()
        }
        return ""
    }

}
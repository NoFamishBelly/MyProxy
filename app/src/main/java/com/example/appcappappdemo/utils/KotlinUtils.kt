package com.example.appcappappdemo.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject

object KotlinUtils {

    /**
     * 将 json对象转化为 String
     *
     * @param t
     * @param <T>
     * @return
    </T> */
    fun <T> jsonToString(t: T): String? {
        val gson = Gson()
        return gson.toJson(t)
    }

    /**
     * 将字符串 转换为 json对象
     *
     * @param jsonStr
     * @param t
     * @param <T>
     * @return
    </T> */
    fun <T> stringToJson(jsonStr: String?, t: T?): T? {
        try {
            val gson = Gson()
            t?.let {
                return gson.fromJson(jsonStr, it::class.java) as T
            }
            return null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    fun getDataStr(dataMap: HashMap<String, String>): String {
        if (dataMap.isNotEmpty()) {
            val str = StringBuilder()
            for (key in dataMap.keys) {
                str.append("$key=${dataMap[key]}&")
            }
            return str.substring(0, str.length - 1)
        }
        return ""
    }


    fun getDataJsonStr(dataMap: HashMap<String, String>): String? {
        if (dataMap.isNotEmpty()) {
            val json = JsonObject()
            for (key in dataMap.keys) {
                json.addProperty(key, dataMap[key])
            }
            return jsonToString(json)
        }
        return ""
    }


    fun getScreenWidth(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.widthPixels
    }


    fun dp2px(dp: Float, context: Context): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

}
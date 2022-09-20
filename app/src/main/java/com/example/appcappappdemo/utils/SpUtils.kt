package com.example.appcappappdemo.utils

import android.content.Context
import android.content.SharedPreferences

object SpUtils {

    private const val SP_MODE = Context.MODE_PRIVATE
    private const val SP_NAME = "aca_sp"

    private var mSharedPreferences: SharedPreferences? = null

    fun isSpEmpty() = mSharedPreferences == null


    fun init(context: Context) {
        mSharedPreferences = context.getSharedPreferences(SP_NAME, SP_MODE)
    }

    fun <T> save(key: String, t: T) {
        mSharedPreferences?.let { sp ->
            val editor = sp.edit()
            when (t) {
                is String -> {
                    editor.putString(key, t)
                }
                is Int -> {
                    editor.putInt(key, t)
                }
                is Long -> {
                    editor.putLong(key, t)
                }
                is Boolean -> {
                    editor.putBoolean(key, t)
                }
                is Float -> {
                    editor.putFloat(key, t)
                }
                else -> {

                }
            }
            editor.commit()
        }
    }


    fun <T> get(key: String, defaultValue: T): T {
        mSharedPreferences?.let { sp ->
            when (defaultValue) {
                is String -> {
                    return sp.getString(key, defaultValue) as T
                }
                is Int -> {
                    return sp.getInt(key, defaultValue) as T
                }
                is Long -> {
                    return sp.getLong(key, defaultValue) as T
                }
                is Boolean -> {
                    return sp.getBoolean(key, defaultValue) as T
                }
                is Float -> {
                    return sp.getFloat(key, defaultValue) as T
                }
                else -> {
                    return defaultValue
                }
            }
        }
        return defaultValue
    }

    fun removeKey(key: String) {
        mSharedPreferences?.let { sp ->
            val editor = sp.edit()
            editor.remove(key)
            editor.commit()
        }
    }

    fun removeAll() {
        mSharedPreferences?.let { sp ->
            val editor = sp.edit()
            editor.clear()
            editor.commit()
        }
    }


}
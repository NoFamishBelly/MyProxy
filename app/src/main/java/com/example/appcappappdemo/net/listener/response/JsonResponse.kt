package com.example.appcappappdemo.net.listener.response

data class JsonResponse<T>(
    val code: String,
    val msg: String,
    val data: T
)
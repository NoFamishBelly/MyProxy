package com.example.appcappappdemo.net.annotation


/**
 * @author lizheng.zhao
 * @date 2022/09/21
 * @description 参数注解
 */
@MustBeDocumented
@Target(AnnotationTarget.VALUE_PARAMETER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Param(val value: String)

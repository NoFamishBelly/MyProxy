package com.example.appcappappdemo.net.annotation


/**
 * @author lizheng.zhao
 * @date 2022/09/21
 * @description 参数注解
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Address(val value: String)

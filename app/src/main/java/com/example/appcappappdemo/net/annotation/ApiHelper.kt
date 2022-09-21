package com.example.appcappappdemo.net.annotation

import com.example.appcappappdemo.net.listener.request.RequestCall
import java.lang.reflect.*

object ApiHelper {

    fun <T> getApi(c: Class<T>): T {
        return Proxy.newProxyInstance(c.classLoader, arrayOf<Class<*>>(c), ApiProxyHandler()) as T
    }


    class ApiProxyHandler : InvocationHandler {
        override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any {
            val returnType = method.returnType
            //判断返回值类型
            return if (returnType == RequestCall::class.java) {
                //获取返回值的泛型类型
                val genericReturnType = method.genericReturnType
                if (genericReturnType is ParameterizedType) {
                    val type =
                        genericReturnType.actualTypeArguments[0]
                    if (type is WildcardType) {
                        //如果是通配符就报错
                        throw RuntimeException("The \"" + method.name + "\" method must explicitly declare the generic parameters of the returned value")
                    }
                    RequestCall<Any>(
                        AnnotationParser.parse(
                            method,
                            args
                        ), type
                    )
                } else {
                    throw RuntimeException("The return value of \"" + method.name + "\" must explicitly declare generic parameters")
                }
            } else {
                throw RuntimeException("The return value type of the \"" + method.name + "\" method must be " + RequestCall::class.java)
            }
        }
    }
}
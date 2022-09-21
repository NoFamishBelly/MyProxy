package com.example.appcappappdemo.net.annotation

import androidx.annotation.NonNull
import com.example.appcappappdemo.net.param.HttpParams
import java.lang.reflect.Method

/**
 * @author lizheng.zhao
 * @date 2022/09/21
 * @description 将参数注解解析到HttpParam中
 */
object AnnotationParser {

    @NonNull
    fun parse(method: Method, args: Array<Any>): HttpParams {
        val params = HttpParams()
        parseMethodAnnotation(method.annotations, params)
        val annotations = method.parameterAnnotations
        for (i in annotations.indices) {
            parseParamsAnnotation(annotations[i], args[i], params)
        }
        return params
    }


    /**
     * 解析方法注解
     */
    private fun parseMethodAnnotation(annotationArray: Array<Annotation>?, httpParams: HttpParams) {
        annotationArray?.let { annotations ->
            if (annotations.isNotEmpty()) {
                for (annotation in annotations) {
                    when (annotation) {
                        is Address -> {
                            httpParams.mUrl = annotation.value
                        }
                    }
                }
            }
        }
    }

    /**
     * 解析参数注解
     */
    private fun parseParamsAnnotation(
        annotationArray: Array<Annotation>?,
        params: Any?,
        httpParams: HttpParams
    ) {
        annotationArray?.let { annotations ->
            params?.let { param ->
                if (annotations.isNotEmpty()) {
                    for (annotation in annotations) {
                        when (annotation) {
                            is Param -> {
                                if (param is String) {
                                    httpParams.mParams[(annotation as Param).value] = param
                                } else {
                                    throw RuntimeException("接口请求参数不是String类型")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
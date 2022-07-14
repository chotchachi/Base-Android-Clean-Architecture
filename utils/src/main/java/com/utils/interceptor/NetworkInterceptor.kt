package com.utils.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Thanh Quang on 28/02/2022.
 */
class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()

        return chain.proceed(builder.build())
    }
}

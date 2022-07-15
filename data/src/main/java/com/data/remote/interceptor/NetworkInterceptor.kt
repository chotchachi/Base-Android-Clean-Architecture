package com.data.remote.interceptor

import com.data.remote.headerProvider.AuthHeaderProvider
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Thanh Quang on 28/02/2022.
 */
class NetworkInterceptor(
    private val authHeaderProvider: AuthHeaderProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()
            .apply { authHeaderProvider.applyHeader(this) }

        return chain.proceed(builder.build())
    }
}

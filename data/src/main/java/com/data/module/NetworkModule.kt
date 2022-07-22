@file:OptIn(ExperimentalSerializationApi::class)

package com.data.module

import android.content.Context
import com.data.BuildConfig
import com.data.remote.TheCatApi
import com.data.remote.NetworkConstant.CACHE_SIZE
import com.data.remote.NetworkConstant.CAT_API_SERVER
import com.data.remote.NetworkConstant.TIME_OUT
import com.data.remote.headerProvider.AuthHeaderProvider
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.data.remote.interceptor.NetworkInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by Thanh Quang on 28/02/2022.
 */
private val BASE_APP_API_URL_QUALIFIER = named("BASE_APP_API_URL_QUALIFIER")

val networkModule = module {
    singleOf(::provideNetworkInterceptor)
    singleOf(::createOkHttpClient)
    singleOf(::createConverterFactory)
    factory(BASE_APP_API_URL_QUALIFIER) { CAT_API_SERVER }
    single { createService<TheCatApi>(get(BASE_APP_API_URL_QUALIFIER), get(), get()) }
}

private fun provideNetworkInterceptor(): NetworkInterceptor {
    val authHeaderProvider = AuthHeaderProvider()
    return NetworkInterceptor(authHeaderProvider = authHeaderProvider)
}

private fun createOkHttpClient(
    context: Context,
    networkInterceptor: NetworkInterceptor
): OkHttpClient {
    val myCache = Cache(context.cacheDir, CACHE_SIZE)
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    val builder = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(networkInterceptor)
        .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)

    return builder.build()
}

private fun createConverterFactory(): Converter.Factory {
    val json = Json { ignoreUnknownKeys = true }
    val contentType = "application/json".toMediaType()
    return json.asConverterFactory(contentType)
}

private inline fun <reified T> createService(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    converterFactory: Converter.Factory
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(T::class.java)
}
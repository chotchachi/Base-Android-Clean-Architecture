package com.data.remote.headerProvider

import okhttp3.Request

/**
 * Created by Thanh Quang on 5/26/21.
 */
class AuthHeaderProvider : HeaderProvider {
    override fun getHeader() = "bed9cfb9-fad4-41ae-bc4d-09cf7cb552bf"

    override fun saveHeader(token: String?) {

    }

    override fun applyHeader(builder: Request.Builder): Request.Builder {
        return builder.apply {
            getHeader().let {
                removeHeader(API_KEY)
                addHeader(API_KEY, it)
            }
        }
    }

    companion object {
        private const val API_KEY = "x-api-key"
    }
}
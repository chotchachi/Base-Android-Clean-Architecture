package com.data.remote.headerProvider

import okhttp3.Request

/**
 * Created by Thanh Quang on 5/26/21.
 */
interface HeaderProvider {
    fun getHeader(): String?
    fun saveHeader(token: String?)
    fun applyHeader(builder: Request.Builder): Request.Builder
}
package com.data.datastore

import android.content.Context

/**
 * Created by Thanh Quang on 20/05/2022.
 */
private const val REMOTE_KEY = "remoteKey"

class AppDataStore(
    context: Context
)  {
    private val dataStoreUtils = DataStoreUtils(context)

    fun getRemoteKeyFlow() = dataStoreUtils.readIntFlow(REMOTE_KEY)

    fun getRemoteKey() = dataStoreUtils.readIntData(REMOTE_KEY)

    fun setRemoteKey(value: Int) = dataStoreUtils.saveSyncIntData(REMOTE_KEY, value)
}
package com.chotchachi.baseandroidcleanarchitecture.initializer

import android.content.Context
import androidx.startup.Initializer
import com.chotchachi.baseandroidcleanarchitecture.BuildConfig
import com.utils.timber.DebugLogTree
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugLogTree())
        }
        Timber.tag("Initializer").d("Timber initialized")
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
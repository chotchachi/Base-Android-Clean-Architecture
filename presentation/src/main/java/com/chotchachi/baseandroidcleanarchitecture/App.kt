package com.chotchachi.baseandroidcleanarchitecture

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.chotchachi.baseandroidcleanarchitecture.di.appModules
import com.utils.timber.DebugLogTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

/**
 * Created by Thanh Quang on 15/07/2022.
 */
class App : Application(),
    ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(appModules)
        }

        // Plant Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugLogTree())
        }
    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .crossfade(true)
        .build()

    companion object {
        lateinit var instance: App
            private set
    }
}
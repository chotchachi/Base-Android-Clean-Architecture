package com.chotchachi.baseandroidcleanarchitecture

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

/**
 * Created by Thanh Quang on 15/07/2022.
 */
class App : Application(),
    ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .crossfade(true)
        .build()

    companion object {
        lateinit var instance: App
            private set
    }
}
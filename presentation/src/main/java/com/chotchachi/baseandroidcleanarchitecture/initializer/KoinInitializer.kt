package com.chotchachi.baseandroidcleanarchitecture.initializer

import android.content.Context
import androidx.startup.Initializer
import com.chotchachi.baseandroidcleanarchitecture.BuildConfig
import com.chotchachi.baseandroidcleanarchitecture.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

@Suppress("unused")
class KoinInitializer : Initializer<Koin> {
    override fun create(context: Context): Koin =
        context.startKoinIfNeeded().also { Timber.tag("Initializer").d("Koin initialized") }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf(TimberInitializer::class.java)
}

/**
 * Start koin if global KoinContext is null.
 * @return [Koin] instance.
 */
fun Context.startKoinIfNeeded(): Koin {
    return GlobalContext.getOrNull() ?: startKoin {
        androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        androidContext(applicationContext)
        modules(appModules)
    }.koin
}
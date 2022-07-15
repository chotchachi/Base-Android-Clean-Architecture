package com.chotchachi.baseandroidcleanarchitecture.di

import com.chotchachi.baseandroidcleanarchitecture.core.DefaultCoroutineDispatchers
import com.core.dispatchers.CoroutineDispatchers
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Thanh Quang on 15/07/2022.
 */
val coreModule = module {
    singleOf(::DefaultCoroutineDispatchers) { bind<CoroutineDispatchers>() }
}
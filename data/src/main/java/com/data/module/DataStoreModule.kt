package com.data.module

import com.data.datastore.AppDataStore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Thanh Quang on 20/05/2022.
 */
val dataStoreModule = module {
    singleOf(::AppDataStore)
}
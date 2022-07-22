package com.chotchachi.baseandroidcleanarchitecture.di

import com.data.module.dataStoreModule
import com.data.module.networkModule
import com.data.module.repositoryModule
import com.data.module.roomModule

/**
 * Created by Thanh Quang on 15/07/2022.
 */
val appModules = listOf(
    coreModule,
    networkModule,
    roomModule,
    dataStoreModule,
    repositoryModule,
    viewModelModule
)
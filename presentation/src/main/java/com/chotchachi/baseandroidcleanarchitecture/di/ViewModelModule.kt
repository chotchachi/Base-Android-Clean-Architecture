package com.chotchachi.baseandroidcleanarchitecture.di

import com.chotchachi.baseandroidcleanarchitecture.ui.home.HomeViewModel
import com.chotchachi.baseandroidcleanarchitecture.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Created by Thanh Quang on 15/07/2022.
 */
val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
}
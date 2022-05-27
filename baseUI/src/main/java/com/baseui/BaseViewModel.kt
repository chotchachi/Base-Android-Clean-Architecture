package com.baseui

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import timber.log.Timber

/**
 * Created by Thanh Quang on 21/05/2022.
 */
abstract class BaseViewModel : ViewModel() {
    init {
        @Suppress("LeakingThis")
        Timber.d("$this::init")
    }

    @CallSuper
    override fun onCleared() {
        Timber.d("$this::onCleared")
    }
}
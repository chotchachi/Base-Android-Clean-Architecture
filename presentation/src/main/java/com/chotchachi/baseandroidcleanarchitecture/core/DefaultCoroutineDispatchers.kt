package com.chotchachi.baseandroidcleanarchitecture.core

import com.core.dispatchers.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers

/**
 * Created by Thanh Quang on 23/05/2022.
 */
class DefaultCoroutineDispatchers : CoroutineDispatchers {
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
}
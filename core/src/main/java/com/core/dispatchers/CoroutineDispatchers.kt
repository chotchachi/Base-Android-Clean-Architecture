package com.core.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Thanh Quang on 22/05/2022.
 */
interface CoroutineDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}
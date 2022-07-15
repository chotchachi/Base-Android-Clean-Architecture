package com.utils.extensions.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

/**
 * Created by Thanh Quang on 17/05/2022.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T, R> Flow<T>.mapTo(value: R): Flow<R> =
    transform { return@transform emit(value) }

@Suppress("NOTHING_TO_INLINE")
inline fun <T> Flow<T>.mapToUnit(): Flow<Unit> = mapTo(Unit)
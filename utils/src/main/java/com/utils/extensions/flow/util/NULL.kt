package com.utils.extensions.flow.util

/**
 * Created by Thanh Quang on 25/07/2022.
 */
object NULL {
    @Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
    inline fun <T> unbox(v: Any?): T = if (this === v) null as T else v as T
}
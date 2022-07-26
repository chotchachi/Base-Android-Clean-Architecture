package com.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Thanh Quang on 23/07/2022.
 */
@Parcelize
data class CatImage(
    val id: String,
    val url: String,
    val width: Long?,
    val height: Long?
) : Parcelable
package com.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Thanh Quang on 25/07/2022.
 */
@Parcelize
data class Vote(
    val id: Int,
    val imageId: String,
    val subId: String,
    val createdAt: String,
    val value: Int,
    val countryCode: String,
    val image: CatImage
) : Parcelable
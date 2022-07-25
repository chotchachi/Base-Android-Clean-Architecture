package com.data.entity

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Thanh Quang on 25/07/2022.
 */
@Serializable
data class VoteEntity(
    val id: Int,

    @SerialName("image_id")
    val imageId: String,

    @SerialName("sub_id")
    val subId: String,

    @SerialName("created_at")
    val createdAt: String,

    val value: Int,

    @SerialName("country_code")
    val countryCode: String,

    @Embedded
    val image: CatImageEntity
)
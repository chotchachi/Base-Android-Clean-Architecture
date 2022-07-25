package com.data.entity.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Thanh Quang on 25/07/2022.
 */
@Serializable
data class VoteRequestEntity(
    @SerialName("image_id")
    val imageId: String,
    @SerialName("sub_id")
    val subId: String,
    val value: Int
)
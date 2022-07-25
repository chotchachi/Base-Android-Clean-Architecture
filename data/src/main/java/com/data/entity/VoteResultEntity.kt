package com.data.entity

import kotlinx.serialization.Serializable

/**
 * Created by Thanh Quang on 25/07/2022.
 */
@Serializable
data class VoteResultEntity(
    val message: String,
    val id: Int
)
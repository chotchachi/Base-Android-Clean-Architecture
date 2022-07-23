package com.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class CatImageEntity(
    val id: String,
    val url: String
)
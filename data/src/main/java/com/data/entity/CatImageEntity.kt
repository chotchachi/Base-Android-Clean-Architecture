package com.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "image")
@Serializable
data class CatImageEntity(
    @PrimaryKey
    @ColumnInfo(name = "image_id")
    val id: String,
    @ColumnInfo(name = "image_url")
    val url: String,
    @ColumnInfo(name = "image_width")
    val width: Long?,
    @ColumnInfo(name = "image_height")
    val height: Long?
)
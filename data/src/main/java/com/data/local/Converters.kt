package com.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

/**
 * Created by Thanh Quang on 12/04/2022.
 */
class Converters {
    @TypeConverter
    fun listStringsToString(strings: List<String>): String {
        return Json.encodeToString(strings)
    }

    @TypeConverter
    fun stringToListStrings(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toDate(dateLong: Long): Date {
        return Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
}

package com.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.data.entity.BreedEntity
import com.data.entity.CatImageEntity
import com.data.local.dao.BreedItemDao

/**
 * Created by Thanh Quang on 21/12/2021.
 */
@Database(
    entities = [
        CatImageEntity::class,
        BreedEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedItemDao(): BreedItemDao
}

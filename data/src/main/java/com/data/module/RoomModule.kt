package com.data.module

import android.content.Context
import androidx.room.Room
import com.data.local.AppDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Thanh Quang on 21/12/2021.
 */
val roomModule = module {
    singleOf(::provideDataBase)
    singleOf(::provideBreedItemDao)
}

private fun provideDataBase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase")
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideBreedItemDao(appDatabase: AppDatabase) = appDatabase.breedItemDao()

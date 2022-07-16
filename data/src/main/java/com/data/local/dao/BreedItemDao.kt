package com.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.data.entity.BreedEntity

/**
 * Created by Thanh Quang on 16/07/2022.
 */
@Dao
interface BreedItemDao {
    @Query("SELECT * FROM breed")
    fun getBreeds(): PagingSource<Int, BreedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreeds(breeds: List<BreedEntity>)

    @Query("DELETE from breed")
    suspend fun deleteAllBreeds()
}
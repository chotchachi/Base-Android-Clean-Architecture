package com.domain.repository

import androidx.paging.PagingData
import com.domain.model.Breed
import kotlinx.coroutines.flow.Flow

/**
 * Created by Thanh Quang on 14/07/2022.
 */
interface BreedRepository {
    fun getBreed(): Flow<PagingData<Breed>>
    fun searchBreed(query: String): Flow<List<Breed>>
}
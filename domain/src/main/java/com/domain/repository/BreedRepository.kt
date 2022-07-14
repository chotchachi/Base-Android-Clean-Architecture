package com.domain.repository

import com.domain.model.Breed
import kotlinx.coroutines.flow.Flow

/**
 * Created by Thanh Quang on 14/07/2022.
 */
interface BreedRepository {
    fun getBreed(attachBreed: Int, page: Int, limit: Int): Flow<List<Breed>>
    fun searchBreed(query: String): Flow<List<Breed>>
}
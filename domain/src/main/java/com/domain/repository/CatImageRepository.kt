package com.domain.repository

import com.domain.model.CatImage
import kotlinx.coroutines.flow.Flow

/**
 * Created by Thanh Quang on 23/07/2022.
 */
interface CatImageRepository {
    fun getCatImagesVoting(): Flow<List<CatImage>>
}
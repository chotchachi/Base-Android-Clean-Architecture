package com.data.repository

import com.core.dispatchers.CoroutineDispatchers
import com.data.entity.mapper.CatImageEntityMapper
import com.data.remote.TheCatApi
import com.domain.repository.CatImageRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Created by Thanh Quang on 23/07/2022.
 */
class CatImageRepositoryImpl(
    private val theCatApi: TheCatApi,
    private val catImageEntityMapper: CatImageEntityMapper,
    private val dispatchers: CoroutineDispatchers
) : CatImageRepository {
    override fun getCatImagesVoting() = flow {
        emit(theCatApi.searchCatImagesAsync(order = "RANDOM", limit = 3).await())
    }
        .catch { emit(emptyList()) }
        .map { it.map(catImageEntityMapper::mapFromEntity) }
        .flowOn(dispatchers.io)
}
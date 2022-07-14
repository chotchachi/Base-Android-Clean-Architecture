package com.data.repository

import com.core.dispatchers.CoroutineDispatchers
import com.data.entity.mapper.BreedEntityMapper
import com.data.remote.CatApi
import com.domain.repository.BreedRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Created by Thanh Quang on 14/07/2022.
 */
class BreedRepositoryImpl(
    private val catApi: CatApi,
    private val breedEntityMapper: BreedEntityMapper,
    private val dispatchers: CoroutineDispatchers
) : BreedRepository {
    override fun getBreed(attachBreed: Int, page: Int, limit: Int) = flow {
        emit(catApi.getBreeds(attachBreed, page, limit))
    }
        .catch { emit(emptyList()) }
        .map { it.map(breedEntityMapper::mapFromEntity) }
        .flowOn(dispatchers.io)

    override fun searchBreed(query: String) = flow {
        emit(catApi.searchBreeds(query))
    }
        .catch { emit(emptyList()) }
        .map { it.map(breedEntityMapper::mapFromEntity) }
        .flowOn(dispatchers.io)
}
package com.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.core.dispatchers.CoroutineDispatchers
import com.data.entity.mapper.BreedEntityMapper
import com.data.paging.BreedPagingSource
import com.data.paging.BreedPagingSource.Companion.NETWORK_PAGE_SIZE
import com.data.remote.CatApi
import com.domain.model.Breed
import com.domain.repository.BreedRepository
import kotlinx.coroutines.flow.*

/**
 * Created by Thanh Quang on 14/07/2022.
 */
class BreedRepositoryImpl(
    private val catApi: CatApi,
    private val breedEntityMapper: BreedEntityMapper,
    private val dispatchers: CoroutineDispatchers
) : BreedRepository {

    override fun getBreed(): Flow<PagingData<Breed>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                BreedPagingSource(catApi, breedEntityMapper)
            }
        ).flow.flowOn(dispatchers.io)
    }

    override fun searchBreed(query: String) = flow {
        emit(catApi.searchBreeds(query))
    }
        .catch { emit(emptyList()) }
        .map { it.map(breedEntityMapper::mapFromEntity) }
        .flowOn(dispatchers.io)
}
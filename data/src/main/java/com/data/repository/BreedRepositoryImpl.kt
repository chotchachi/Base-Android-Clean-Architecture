package com.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.core.dispatchers.CoroutineDispatchers
import com.data.entity.mapper.BreedMapper
import com.data.paging.BreedPagingSource
import com.data.paging.BreedPagingSource.Companion.NETWORK_PAGE_SIZE
import com.data.remote.TheCatApi
import com.domain.model.Breed
import com.domain.repository.BreedRepository
import kotlinx.coroutines.flow.*

/**
 * Created by Thanh Quang on 14/07/2022.
 */
class BreedRepositoryImpl(
    private val theCatApi: TheCatApi,
    private val breedMapper: BreedMapper,
    private val dispatchers: CoroutineDispatchers
) : BreedRepository {

    override fun getBreed(): Flow<PagingData<Breed>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                BreedPagingSource(theCatApi, breedMapper)
            }
        ).flow.flowOn(dispatchers.io)
    }

    override fun searchBreed(query: String) = flow {
        emit(theCatApi.searchBreedsAsync(query).await())
    }
        .catch { emit(emptyList()) }
        .map { it.map(breedMapper::mapFromEntity) }
        .flowOn(dispatchers.io)
}
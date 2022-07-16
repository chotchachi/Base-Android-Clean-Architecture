package com.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.data.entity.mapper.BreedEntityMapper
import com.data.local.dao.BreedItemDao
import com.data.remote.TheCatApi
import com.domain.model.Breed

/**
 * Created by Thanh Quang on 16/07/2022.
 */
@OptIn(ExperimentalPagingApi::class)
class BreedRemoteMediator(
    private val theCatApi: TheCatApi,
    private val breedItemDao: BreedItemDao,
    private val breedEntityMapper: BreedEntityMapper
) : RemoteMediator<Int, Breed>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Breed>): MediatorResult {
        TODO("Not yet implemented")
    }
}
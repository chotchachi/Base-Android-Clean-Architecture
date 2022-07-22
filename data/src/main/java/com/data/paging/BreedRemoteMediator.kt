package com.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.data.datastore.AppDataStore
import com.data.entity.BreedEntity
import com.data.local.AppDatabase
import com.data.remote.TheCatApi
import timber.log.Timber

/**
 * Created by Thanh Quang on 16/07/2022.
 */
@OptIn(ExperimentalPagingApi::class)
class BreedRemoteMediator(
    private val theCatApi: TheCatApi,
    private val appDatabase: AppDatabase,
    private val appDataStore: AppDataStore
) : RemoteMediator<Int, BreedEntity>() {
    private val breedItemDao = appDatabase.breedItemDao()

    private var currentPage: Int? = 0

    override suspend fun load(loadType: LoadType, state: PagingState<Int, BreedEntity>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = currentPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    remoteKey
                }
            }

            Timber.d("$loadKey")

            val response = theCatApi.getBreeds(
                attachBreed = 0,
                page = loadKey,
                limit = 10
            )

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    breedItemDao.deleteAllBreeds()
                    currentPage = null
                }

                currentPage = loadKey.plus(1)

                breedItemDao.insertBreeds(response)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.isEmpty()
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
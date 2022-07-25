package com.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.data.entity.mapper.BreedMapper
import com.data.remote.TheCatApi
import com.domain.model.Breed

/**
 * Created by Thanh Quang on 15/07/2022.
 */
class BreedPagingSource(
    private val theCatApi: TheCatApi,
    private val breedMapper: BreedMapper
) :
    PagingSource<Int, Breed>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        val currentPage = params.key ?: INITIAL_LOAD_PAGE

        return try {
            val response = theCatApi.getBreedsAsync(
                attachBreed = 0,
                page = currentPage,
                limit = params.loadSize
            ).await().map(breedMapper::mapFromEntity)

            val nextKey = if (response.isEmpty()) {
                null
            } else {
                currentPage + 1
            }
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == INITIAL_LOAD_PAGE) null else currentPage - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? {
        return null
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
        private const val INITIAL_LOAD_PAGE = 0
    }
}
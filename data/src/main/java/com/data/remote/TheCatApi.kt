package com.data.remote

import com.data.entity.BreedEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Thanh Quang on 14/07/2022.
 */
interface TheCatApi {
    @GET("breeds")
    fun getBreedsAsync(
        @Query("attach_breed") attachBreed: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Deferred<List<BreedEntity>>

    @GET("breeds/search")
    suspend fun searchBreeds(
        @Query("q") query: String
    ): List<BreedEntity>
}
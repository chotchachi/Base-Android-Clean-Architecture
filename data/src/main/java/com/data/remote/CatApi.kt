package com.data.remote

import com.data.entity.BreedEntity
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Thanh Quang on 14/07/2022.
 */
interface CatApi {
    @GET("breeds")
    suspend fun getBreeds(
        @Query("attach_breed") attachBreed: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<BreedEntity>

    @GET("breeds/search")
    suspend fun searchBreeds(
        @Query("q") query: String
    ): List<BreedEntity>
}
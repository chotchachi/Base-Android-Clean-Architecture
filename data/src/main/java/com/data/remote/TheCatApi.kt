package com.data.remote

import com.data.entity.BreedEntity
import com.data.entity.CatImageEntity
import com.data.entity.VoteEntity
import com.data.entity.VoteResultEntity
import com.data.entity.request.VoteRequestEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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
    fun searchBreedsAsync(
        @Query("q") query: String
    ): Deferred<List<BreedEntity>>

    @GET("images/search")
    fun searchCatImagesAsync(
        @Query("size") size: String? = null,
        @Query("mime_types") mimeTypes: List<String>? = null,
        @Query("order") order: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null,
        @Query("category_ids") categoryIds: List<Int>? = null,
        @Query("breed_id") breedId: String? = null
    ): Deferred<List<CatImageEntity>>

    @POST("votes")
    fun sendVoteAsync(
        @Body voteRequest: VoteRequestEntity
    ): Deferred<VoteResultEntity>

    @GET("votes")
    fun getVotesAsync(
        @Query("sub_id") subId: String
    ): Deferred<List<VoteEntity>>
}
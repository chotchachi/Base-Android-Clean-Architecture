package com.data.repository

import com.core.dispatchers.CoroutineDispatchers
import com.data.entity.mapper.VoteMapper
import com.data.entity.mapper.VoteRequestMapper
import com.data.entity.mapper.VoteResultMapper
import com.data.remote.TheCatApi
import com.domain.model.request.VoteRequest
import com.domain.repository.VoteRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Created by Thanh Quang on 25/07/2022.
 */
class VoteRepositoryImpl(
    private val theCatApi: TheCatApi,
    private val voteRequestMapper: VoteRequestMapper,
    private val voteResultMapper: VoteResultMapper,
    private val voteMapper: VoteMapper,
    private val dispatchers: CoroutineDispatchers
) : VoteRepository {
    override fun sendVote(voteRequest: VoteRequest) = flow {
        emit(theCatApi.sendVoteAsync(voteRequestMapper.mapToEntity(voteRequest)).await())
    }
        .map(voteResultMapper::mapFromEntity)
        .flowOn(dispatchers.io)

    override fun getVotes(subId: String) = flow {
        emit(theCatApi.getVotesAsync(subId).await())
    }
        .map { it.map(voteMapper::mapFromEntity) }
        .flowOn(dispatchers.io)
}
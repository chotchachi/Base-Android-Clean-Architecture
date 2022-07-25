package com.domain.repository

import com.domain.model.Vote
import com.domain.model.VoteResult
import com.domain.model.request.VoteRequest
import kotlinx.coroutines.flow.Flow

/**
 * Created by Thanh Quang on 25/07/2022.
 */
interface VoteRepository {
    fun sendVote(voteRequest: VoteRequest): Flow<VoteResult>
    fun getVotes(subId: String): Flow<List<Vote>>
}
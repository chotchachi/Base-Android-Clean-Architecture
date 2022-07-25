package com.domain.model.request

/**
 * Created by Thanh Quang on 25/07/2022.
 */
data class VoteRequest(
    val imageId: String,
    val subId: String,
    val value: VoteValue
) {
    enum class VoteValue(val raw: Int) {
        DOWN(0), UP(1)
    }
}

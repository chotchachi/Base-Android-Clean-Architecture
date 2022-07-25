package com.data.entity.mapper

import com.core.mapper.Mapper
import com.data.entity.request.VoteRequestEntity
import com.domain.model.request.VoteRequest

/**
 * Created by Thanh Quang on 25/07/2022.
 */
class VoteRequestMapper : Mapper<VoteRequestEntity, VoteRequest> {
    override fun mapFromEntity(item: VoteRequestEntity) = VoteRequest(
        item.imageId,
        item.subId,
        VoteRequest.VoteValue.values().first { it.raw == item.value })

    override fun mapToEntity(item: VoteRequest) = VoteRequestEntity(
        item.imageId,
        item.subId,
        item.value.raw
    )
}
package com.data.entity.mapper

import com.core.mapper.Mapper
import com.data.entity.VoteResultEntity
import com.domain.model.VoteResult

/**
 * Created by Thanh Quang on 25/07/2022.
 */
class VoteResultMapper : Mapper<VoteResultEntity, VoteResult> {
    override fun mapFromEntity(item: VoteResultEntity) = VoteResult(item.message, item.id)

    override fun mapToEntity(item: VoteResult) = VoteResultEntity(item.message, item.id)
}
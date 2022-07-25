package com.data.entity.mapper

import com.core.mapper.Mapper
import com.data.entity.VoteEntity
import com.domain.model.Vote

/**
 * Created by Thanh Quang on 25/07/2022.
 */
class VoteMapper(
    private val catImageMapper: CatImageMapper
) : Mapper<VoteEntity, Vote> {
    override fun mapFromEntity(item: VoteEntity) = Vote(
        item.id,
        item.imageId,
        item.subId,
        item.createdAt,
        item.value,
        item.countryCode,
        catImageMapper.mapFromEntity(item.image)
    )

    override fun mapToEntity(item: Vote) = VoteEntity(
        item.id,
        item.imageId,
        item.subId,
        item.createdAt,
        item.value,
        item.countryCode,
        catImageMapper.mapToEntity(item.image)
    )
}
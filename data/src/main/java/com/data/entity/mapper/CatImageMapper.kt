package com.data.entity.mapper

import com.core.mapper.Mapper
import com.data.entity.CatImageEntity
import com.domain.model.CatImage

/**
 * Created by Thanh Quang on 23/07/2022.
 */
class CatImageMapper : Mapper<CatImageEntity, CatImage> {
    override fun mapFromEntity(item: CatImageEntity) = CatImage(item.id, item.url)

    override fun mapToEntity(item: CatImage) = CatImageEntity(item.id, item.url)
}
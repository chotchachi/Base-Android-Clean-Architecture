package com.core.mapper

/**
 * Created by Thanh Quang on 12/04/2022.
 */
interface Mapper<E, D> {
    fun mapFromEntity(item: E): D
    fun mapToEntity(item: D): E
}

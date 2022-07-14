package com.data.module

import com.data.entity.mapper.BreedEntityMapper
import com.data.repository.*
import com.domain.repository.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Thanh Quang on 17/12/2021.
 */
val repositoryModule = module {
    singleOf(::BreedEntityMapper)

    singleOf(::BreedRepositoryImpl) { bind<BreedRepository>() }
}

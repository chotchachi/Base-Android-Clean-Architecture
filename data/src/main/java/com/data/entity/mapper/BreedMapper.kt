package com.data.entity.mapper

import com.core.mapper.Mapper
import com.data.entity.BreedEntity
import com.domain.model.Breed

/**
 * Created by Thanh Quang on 14/07/2022.
 */
class BreedMapper(
    private val catImageMapper: CatImageMapper
) : Mapper<BreedEntity, Breed> {
    override fun mapFromEntity(item: BreedEntity) = Breed(
        Breed.Weight(item.weight.imperial, item.weight.metric),
        item.id,
        item.name,
        item.cfaURL,
        item.vetstreetURL,
        item.vcahospitalsURL,
        item.temperament,
        item.origin,
        item.countryCodes,
        item.countryCode,
        item.description,
        item.lifeSpan,
        item.indoor,
        item.lap,
        item.altNames,
        item.adaptability,
        item.affectionLevel,
        item.childFriendly,
        item.dogFriendly,
        item.energyLevel,
        item.grooming,
        item.healthIssues,
        item.intelligence,
        item.sheddingLevel,
        item.socialNeeds,
        item.strangerFriendly,
        item.vocalisation,
        item.experimental,
        item.hairless,
        item.natural,
        item.rare,
        item.rex,
        item.suppressedTail,
        item.shortLegs,
        item.wikipediaURL,
        item.hypoallergenic,
        item.referenceImageID,
        item.image?.let(catImageMapper::mapFromEntity),
        item.catFriendly,
        item.bidability
    )

    override fun mapToEntity(item: Breed) = BreedEntity(
        BreedEntity.Weight(item.weight.imperial, item.weight.metric),
        item.id,
        item.name,
        item.cfaURL,
        item.vetstreetURL,
        item.vcahospitalsURL,
        item.temperament,
        item.origin,
        item.countryCodes,
        item.countryCode,
        item.description,
        item.lifeSpan,
        item.indoor,
        item.lap,
        item.altNames,
        item.adaptability,
        item.affectionLevel,
        item.childFriendly,
        item.dogFriendly,
        item.energyLevel,
        item.grooming,
        item.healthIssues,
        item.intelligence,
        item.sheddingLevel,
        item.socialNeeds,
        item.strangerFriendly,
        item.vocalisation,
        item.experimental,
        item.hairless,
        item.natural,
        item.rare,
        item.rex,
        item.suppressedTail,
        item.shortLegs,
        item.wikipediaURL,
        item.hypoallergenic,
        item.referenceImageID,
        item.image?.let(catImageMapper::mapToEntity),
        item.catFriendly,
        item.bidability
    )
}
package com.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Thanh Quang on 14/07/2022.
 */
@Entity(tableName = "breed")
@Serializable
data class BreedEntity(
    val weight: Weight,

    @PrimaryKey
    val id: String,
    val name: String,

    @SerialName("cfa_url")
    val cfaURL: String? = null,

    @SerialName("vetstreet_url")
    val vetstreetURL: String? = null,

    @SerialName("vcahospitals_url")
    val vcahospitalsURL: String? = null,

    val temperament: String,
    val origin: String,

    @SerialName("country_codes")
    val countryCodes: String,

    @SerialName("country_code")
    val countryCode: String,

    val description: String,

    @SerialName("life_span")
    val lifeSpan: String,

    val indoor: Long,
    val lap: Long? = null,

    @SerialName("alt_names")
    val altNames: String? = null,

    val adaptability: Long,

    @SerialName("affection_level")
    val affectionLevel: Long,

    @SerialName("child_friendly")
    val childFriendly: Long,

    @SerialName("dog_friendly")
    val dogFriendly: Long,

    @SerialName("energy_level")
    val energyLevel: Long,

    val grooming: Long,

    @SerialName("health_issues")
    val healthIssues: Long,

    val intelligence: Long,

    @SerialName("shedding_level")
    val sheddingLevel: Long,

    @SerialName("social_needs")
    val socialNeeds: Long,

    @SerialName("stranger_friendly")
    val strangerFriendly: Long,

    val vocalisation: Long,
    val experimental: Long,
    val hairless: Long,
    val natural: Long,
    val rare: Long,
    val rex: Long,

    @SerialName("suppressed_tail")
    val suppressedTail: Long,

    @SerialName("short_legs")
    val shortLegs: Long,

    @SerialName("wikipedia_url")
    val wikipediaURL: String? = null,

    val hypoallergenic: Long,

    @SerialName("reference_image_id")
    val referenceImageID: String? = null,

    val image: Image? = null,

    @SerialName("cat_friendly")
    val catFriendly: Long? = null,

    val bidability: Long? = null
) {

    @Serializable
    data class Weight(
        val imperial: String,
        val metric: String
    )

    @Serializable
    data class Image(
        val id: String,
        val width: Long,
        val height: Long,
        val url: String
    )
}

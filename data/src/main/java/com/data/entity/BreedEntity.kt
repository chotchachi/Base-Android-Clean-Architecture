package com.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
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
    @Embedded
    val weight: Weight,

    @PrimaryKey
    val id: String,
    val name: String,

    @SerialName("cfa_url")
    @ColumnInfo(name = "cfa_url")
    val cfaURL: String? = null,

    @SerialName("vetstreet_url")
    @ColumnInfo(name = "vetstreet_url")
    val vetstreetURL: String? = null,

    @SerialName("vcahospitals_url")
    @ColumnInfo(name = "vcahospitals_url")
    val vcahospitalsURL: String? = null,

    val temperament: String,
    val origin: String,

    @SerialName("country_codes")
    @ColumnInfo(name = "country_codes")
    val countryCodes: String,

    @SerialName("country_code")
    @ColumnInfo(name = "country_code")
    val countryCode: String,

    val description: String,

    @SerialName("life_span")
    @ColumnInfo(name = "life_span")
    val lifeSpan: String,

    val indoor: Long,
    val lap: Long? = null,

    @SerialName("alt_names")
    @ColumnInfo(name = "alt_names")
    val altNames: String? = null,

    val adaptability: Long,

    @SerialName("affection_level")
    @ColumnInfo(name = "affection_level")
    val affectionLevel: Long,

    @SerialName("child_friendly")
    @ColumnInfo(name = "child_friendly")
    val childFriendly: Long,

    @SerialName("dog_friendly")
    @ColumnInfo(name = "dog_friendly")
    val dogFriendly: Long,

    @SerialName("energy_level")
    @ColumnInfo(name = "energy_level")
    val energyLevel: Long,

    val grooming: Long,

    @SerialName("health_issues")
    @ColumnInfo(name = "health_issues")
    val healthIssues: Long,

    val intelligence: Long,

    @SerialName("shedding_level")
    @ColumnInfo(name = "shedding_level")
    val sheddingLevel: Long,

    @SerialName("social_needs")
    @ColumnInfo(name = "social_needs")
    val socialNeeds: Long,

    @SerialName("stranger_friendly")
    @ColumnInfo(name = "stranger_friendly")
    val strangerFriendly: Long,

    val vocalisation: Long,
    val experimental: Long,
    val hairless: Long,
    val natural: Long,
    val rare: Long,
    val rex: Long,

    @SerialName("suppressed_tail")
    @ColumnInfo(name = "suppressed_tail")
    val suppressedTail: Long,

    @SerialName("short_legs")
    @ColumnInfo(name = "short_legs")
    val shortLegs: Long,

    @SerialName("wikipedia_url")
    @ColumnInfo(name = "wikipedia_url")
    val wikipediaURL: String? = null,

    val hypoallergenic: Long,

    @SerialName("reference_image_id")
    @ColumnInfo(name = "reference_image_id")
    val referenceImageID: String? = null,

    @Embedded
    val image: Image? = null,

    @SerialName("cat_friendly")
    @ColumnInfo(name = "cat_friendly")
    val catFriendly: Long? = null,

    val bidability: Long? = null
) {

    @Serializable
    data class Weight(
        @ColumnInfo(name = "weight_imperial")
        val imperial: String,
        @ColumnInfo(name = "weight_metric")
        val metric: String
    )

    @Serializable
    data class Image(
        @ColumnInfo(name = "image_id")
        val id: String,
        @ColumnInfo(name = "image_width")
        val width: Long,
        @ColumnInfo(name = "image_height")
        val height: Long,
        @ColumnInfo(name = "image_url")
        val url: String
    )
}

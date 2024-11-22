package com.superheroes.data

import com.google.gson.annotations.SerializedName

data class SuperheroDetailsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats:SuperheroPowersStatsResponse,
    @SerializedName("biography") val biography:SuperheroBiographyResponse,
    @SerializedName("image") val superheroImage: SuperheroImageDetailResponse
) {}


data class SuperheroPowersStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
) {}

data class SuperheroBiographyResponse(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("alter-egos") val alterEgos: String,
    @SerializedName("aliases") val aliases: MutableList<String> = mutableListOf(),
    @SerializedName("place-of-birth") val place_of_birth: String,
    @SerializedName("first-appearance") val firstAppearance: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("alignment") val alignment: String,
) {}

data class SuperheroImageDetailResponse (
    @SerializedName("url") val url: String
){}
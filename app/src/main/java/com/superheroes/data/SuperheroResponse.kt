package com.superheroes.data

import com.google.gson.annotations.SerializedName

data class SuperheroResponse  (
    @SerializedName("response") val response: String,
    @SerializedName("results-for") val name: String,
    @SerializedName("results") val superheros: List<SuperheroItem>
){}

data class SuperheroItem (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val superheroImage: SuperheroImage
) {

}

data class SuperheroImage (
    @SerializedName("url") val url: String
){}
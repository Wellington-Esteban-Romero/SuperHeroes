package com.superheroes.data

import com.google.gson.annotations.SerializedName

class SuperheroResponse  (
    @SerializedName("response") val response: String,
    @SerializedName("results-for") val name: String,
    @SerializedName("results") val superheros: List<Superhero>
){}

class Superhero (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
) {

}
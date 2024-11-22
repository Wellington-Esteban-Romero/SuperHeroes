package com.superheroes.data

import com.google.gson.annotations.SerializedName

class SuperheroDetailsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats:SuperheroPowersStatsResponse,
) {}


class SuperheroPowersStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String,
) {}
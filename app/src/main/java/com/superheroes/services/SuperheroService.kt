package com.superheroes.services

import com.superheroes.data.SuperheroResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SuperheroService {

    @GET("search/{name}")
    suspend fun findSuperheroesByName(@Path("name") name:String): SuperheroResponse
}
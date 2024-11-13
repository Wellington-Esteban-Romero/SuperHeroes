package com.superheroes.services

import com.superheroes.data.SuperheroItem
import com.superheroes.data.SuperheroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroService {

    @GET("search/{name}")
    suspend fun findSuperheroesByName(@Path("name") name:String): Response<SuperheroResponse>

    @GET("{character-id}")
    suspend fun findSuperheroesById(@Path("character-id}") id:String): Response<SuperheroItem>
}
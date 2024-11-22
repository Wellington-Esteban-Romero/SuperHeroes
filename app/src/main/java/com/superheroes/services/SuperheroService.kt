package com.superheroes.services

import com.superheroes.data.SuperheroDetailsResponse
import com.superheroes.data.SuperheroItem
import com.superheroes.data.SuperheroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroService {

    @GET("search/{name}")
    suspend fun findSuperheroesByName(@Path("name") name:String): Response<SuperheroResponse>

    @GET("{id}")
    suspend fun findSuperheroesById(@Path("id") id:String): Response<SuperheroDetailsResponse>
}
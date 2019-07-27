package com.ronaldo.coelho.garcia.pokemonpo

import retrofit2.Call
import retrofit2.http.*

interface Service {

    @POST("/jokenpokemon/pontuacao")
    fun createPlayerRanking(@Body ranking: Ranking): Call<Void>

    @GET("/jokenpokemon/pontuacao")
    fun retrieveRanking(): Call<List<Ranking>>
}
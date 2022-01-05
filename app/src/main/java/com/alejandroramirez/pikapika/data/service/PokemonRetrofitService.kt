package com.alejandroramirez.pikapika.data.service

import com.alejandroramirez.pikapika.data.model.PokemonDetailApiModel
import com.alejandroramirez.pikapika.data.model.PokemonsResponseApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface PokemonRetrofitService {
    @GET("pokemon?limit=20/")
    fun getPokemons(): Call<PokemonsResponseApiModel>

    @GET
    fun getPokemonByUrl(@Url url: String): Call<PokemonDetailApiModel>

    companion object {
        const val API_URL = "https://pokeapi.co/api/v2/"
    }
}
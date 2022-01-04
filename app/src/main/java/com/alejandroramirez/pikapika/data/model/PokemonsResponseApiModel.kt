package com.alejandroramirez.pikapika.data.model

import com.google.gson.annotations.SerializedName


data class PokemonsResponseApiModel(@SerializedName("results") val pokemons: List<PokemonApiModel>)
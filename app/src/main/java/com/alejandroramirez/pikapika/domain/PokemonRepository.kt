package com.alejandroramirez.pikapika.domain

import com.alejandroramirez.pikapika.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow


interface PokemonRepository {
    fun getPokemons() : Flow<Pokemon>
}
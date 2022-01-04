package com.alejandroramirez.pikapika.data

import com.alejandroramirez.pikapika.domain.PokemonRepository
import com.alejandroramirez.pikapika.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PokemonRepository : PokemonRepository {
    override fun getPokemons(): Flow<List<Pokemon>> = flow {
        emit(listOf(Pokemon("id", "pikachu", "el mejon")))
    }
}
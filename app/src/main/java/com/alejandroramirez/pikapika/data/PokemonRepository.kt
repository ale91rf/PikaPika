package com.alejandroramirez.pikapika.data

import com.alejandroramirez.pikapika.domain.PokemonRepository
import com.alejandroramirez.pikapika.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow


class PokemonRepository : PokemonRepository {
    override fun getPokemons(): Flow<Pokemon> {
        TODO("Not yet implemented")
    }
}
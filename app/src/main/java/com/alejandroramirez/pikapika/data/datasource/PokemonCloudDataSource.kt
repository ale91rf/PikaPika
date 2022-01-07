package com.alejandroramirez.pikapika.data.datasource

import com.alejandroramirez.pikapika.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow


interface PokemonCloudDataSource {
    fun getPokemons(): Flow<List<Pokemon>>
    fun getPokemonById(id: String): Flow<Pokemon>
}
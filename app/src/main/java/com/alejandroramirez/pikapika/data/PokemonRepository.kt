package com.alejandroramirez.pikapika.data

import com.alejandroramirez.pikapika.data.datasource.PokemonCloudDataSource
import com.alejandroramirez.pikapika.domain.PokemonRepository
import com.alejandroramirez.pikapika.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepository(
    private val pokemonCloudDataSource: PokemonCloudDataSource
) : PokemonRepository {
    override fun getPokemons(): Flow<List<Pokemon>> = pokemonCloudDataSource.getPokemons()
}
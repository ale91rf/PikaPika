package com.alejandroramirez.pikapika.data.datasource

import com.alejandroramirez.pikapika.data.service.PokemonRetrofitService
import com.alejandroramirez.pikapika.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PokemonRetrofitDataSource(
    private val pokemonRetrofitService: PokemonRetrofitService
) : PokemonCloudDataSource {
    override fun getPokemons(): Flow<List<Pokemon>> = flow {
        emit(listOf(Pokemon("id", "pikachu", "el mejon")))
    }
}
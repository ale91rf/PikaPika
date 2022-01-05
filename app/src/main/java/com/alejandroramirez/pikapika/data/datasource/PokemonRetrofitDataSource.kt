package com.alejandroramirez.pikapika.data.datasource

import com.alejandroramirez.pikapika.data.mapper.mapPokemonToDomain
import com.alejandroramirez.pikapika.data.service.PokemonRetrofitService
import com.alejandroramirez.pikapika.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PokemonRetrofitDataSource(
    private val pokemonRetrofitService: PokemonRetrofitService
) : PokemonCloudDataSource {
    override fun getPokemons(): Flow<List<Pokemon>> = flow {
        val pokemons = pokemonRetrofitService.getPokemons().execute().body()!!.pokemons
        emit(pokemons.map { pokemonApiModel ->
            pokemonRetrofitService.getPokemonByUrl(pokemonApiModel.urlDetail).execute()
                .body()!!
        }.map { pokemonDetailApiModel ->
            mapPokemonToDomain(pokemonDetailApiModel)
        })
    }
}
package com.alejandroramirez.pikapika.domain.usecase

import com.alejandroramirez.pikapika.domain.PokemonRepository


class GetPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke() = pokemonRepository.getPokemons()
}
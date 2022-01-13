package com.alejandroramirez.pikapika.domain.usecase

import com.alejandroramirez.pikapika.domain.PokemonRepository


class GetPokemonByIdUseCase(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(id: String) = pokemonRepository.getPokemonById(id)
}
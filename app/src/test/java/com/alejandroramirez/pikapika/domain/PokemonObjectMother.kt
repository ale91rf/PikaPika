package com.alejandroramirez.pikapika.domain

import com.alejandroramirez.pikapika.domain.model.Pokemon

class PokemonObjectMother {
    companion object {
        fun createPokemon(
            id: Int = 0,
            name: String = "fake name",
            height: Int = 10,
            weight: Int = 10,
            imageUrl: String = "imageurl.com"
        ) = Pokemon(
            id,
            name,
            height,
            weight,
            imageUrl
        )
    }
}
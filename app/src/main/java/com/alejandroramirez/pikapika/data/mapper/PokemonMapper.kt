package com.alejandroramirez.pikapika.data.mapper

import com.alejandroramirez.pikapika.data.model.PokemonDetailApiModel
import com.alejandroramirez.pikapika.domain.model.Pokemon


fun mapPokemonToDomain(pokemonDetailApiModel: PokemonDetailApiModel) = Pokemon(
    id = pokemonDetailApiModel.id,
    name = pokemonDetailApiModel.name,
    height = pokemonDetailApiModel.height,
    weight = pokemonDetailApiModel.weight,
    imageUrl = pokemonDetailApiModel.sprites.otherSprites.officialArtwork.image
)
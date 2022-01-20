package com.alejandroramirez.pikapika.data.mapper

import com.alejandroramirez.pikapika.data.model.OfficialArtwork
import com.alejandroramirez.pikapika.data.model.OtherSprites
import com.alejandroramirez.pikapika.data.model.PokemonDetailApiModel
import com.alejandroramirez.pikapika.data.model.Sprites
import com.alejandroramirez.pikapika.domain.PokemonObjectMother
import org.junit.Assert.assertEquals
import org.junit.Test


class PokemonMapperTest {

    @Test
    fun `should return a domain pokemon when invoking`() {
        val expectedResult = PokemonObjectMother
            .createPokemon(
                id = POKEMON_ID,
                name = POKEMON_NAME,
                height = POKEMON_HEIGHT,
                weight = POKEMON_WEIGHT,
                imageUrl = OFFICIAL_ART_WORK_IMAGE
            )
        val officialArtwork = OfficialArtwork(OFFICIAL_ART_WORK_IMAGE)
        val otherSprites = OtherSprites(officialArtwork)
        val sprites = Sprites(SPRITES_IMAGE, otherSprites)
        val apiModel = PokemonDetailApiModel(
            id = POKEMON_ID,
            name = POKEMON_NAME,
            height = POKEMON_HEIGHT,
            weight = POKEMON_WEIGHT,
            sprites = sprites
        )

        val actualResult = mapPokemonToDomain(apiModel)

        assertEquals(expectedResult, actualResult)
    }

    private companion object {
        const val POKEMON_ID = 1
        const val POKEMON_NAME = "name"
        const val POKEMON_HEIGHT = 15
        const val POKEMON_WEIGHT = 55
        const val OFFICIAL_ART_WORK_IMAGE = "official art work image"
        const val SPRITES_IMAGE = "sprites image"
    }
}
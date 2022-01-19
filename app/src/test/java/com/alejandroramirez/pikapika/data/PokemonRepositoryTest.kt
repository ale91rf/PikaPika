package com.alejandroramirez.pikapika.data

import com.alejandroramirez.pikapika.data.datasource.PokemonCloudDataSource
import com.alejandroramirez.pikapika.domain.PokemonObjectMother
import com.alejandroramirez.pikapika.domain.PokemonRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class PokemonRepositoryTest {

    private val pokemonCloudDataSource = mock<PokemonCloudDataSource>()
    private val pokemonRepository: PokemonRepository =
        PokemonRepository(pokemonCloudDataSource)

    @Test
    fun `should get pokemons when invoking`() = runBlockingTest {
        val expectedResult = listOf(
            PokemonObjectMother.createPokemon()
        )
        whenever(pokemonCloudDataSource.getPokemons())
            .thenReturn(flowOf(expectedResult))

        val actualResult = pokemonRepository.getPokemons()

        actualResult.collect {
            assertEquals(expectedResult, it)
        }
    }
}
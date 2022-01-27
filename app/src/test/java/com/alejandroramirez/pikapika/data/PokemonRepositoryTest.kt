package com.alejandroramirez.pikapika.data

import com.alejandroramirez.pikapika.data.datasource.PokemonCloudDataSource
import com.alejandroramirez.pikapika.domain.PokemonObjectMother
import com.alejandroramirez.pikapika.domain.PokemonRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class PokemonRepositoryTest {

    private val pokemonCloudDataSource = mock<PokemonCloudDataSource>()
    private val pokemonRepository: PokemonRepository =
        PokemonRepository(pokemonCloudDataSource)

    @Test
    fun `should get pokemons when invoking and got pokemons from cloud data source`() = runTest {
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

    @Test(expected = Exception::class)
    fun `should return an error when got an error from cloud data source getting pokemons`() =
        runTest {
            whenever(pokemonCloudDataSource.getPokemons()).thenReturn(flow { throw Exception() })

            pokemonRepository.getPokemons().collect()
        }

    @Test
    fun `should return a pokemon when invoking by id and got that pokemon from cloud data source`() = runTest {
        val pokemonId = 1
        val expectedResult = PokemonObjectMother.createPokemon(id = pokemonId)
        whenever(pokemonCloudDataSource.getPokemonById(pokemonId.toString()))
            .thenReturn(flowOf(expectedResult))

        val actualResult = pokemonRepository.getPokemonById(pokemonId.toString())

        actualResult.collect {
            assertEquals(expectedResult, it)
        }
    }

    @Test(expected = Exception::class)
    fun `should return an error when got an error from cloud data source getting a pokemon by id`() =
        runTest {
            val pokemonId = "1"
            whenever(pokemonCloudDataSource.getPokemonById(pokemonId))
                .thenReturn(flow { throw Exception() })

            pokemonRepository.getPokemonById(pokemonId).collect()
        }
}
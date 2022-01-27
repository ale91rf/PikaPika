package com.alejandroramirez.pikapika.domain.usecase

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

class GetPokemonsUseCaseTest {

    private val pokemonRepository = mock<PokemonRepository>()
    private val getPokemonsUseCase = GetPokemonsUseCase(pokemonRepository)

    @Test
    fun `should return pokemons when invoking and got pokemons from repository`() = runTest {
        val expectedResult = listOf(
            PokemonObjectMother.createPokemon()
        )
        whenever(pokemonRepository.getPokemons())
            .thenReturn(flowOf(expectedResult))

        val actualResult = getPokemonsUseCase()

        actualResult.collect {
            assertEquals(expectedResult, it)
        }
    }

    @Test(expected = Exception::class)
    fun `should return an error when got an error from the repository getting pokemons`() =
        runTest {
            whenever(pokemonRepository.getPokemons()).thenReturn(flow { throw Exception() })

            getPokemonsUseCase().collect()
        }
}
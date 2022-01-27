package com.alejandroramirez.pikapika.domain.usecase

import com.alejandroramirez.pikapika.domain.PokemonObjectMother
import com.alejandroramirez.pikapika.domain.PokemonRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetPokemonByIdUseCaseTest {

    private val pokemonRepository = mock<PokemonRepository>()
    private val getPokemonByIdUseCase = GetPokemonByIdUseCase(pokemonRepository)

    @Test
    fun `should return a pokemon when invoking by id and got that pokemon from repository`() =
        runTest {
            val pokemonId = 1
            val expectedResult = PokemonObjectMother.createPokemon(id = pokemonId)
            whenever(pokemonRepository.getPokemonById(pokemonId.toString()))
                .thenReturn(flowOf(expectedResult))

            val actualResult = getPokemonByIdUseCase(pokemonId.toString())

            actualResult.collect {
                Assert.assertEquals(expectedResult, it)
            }
        }

    @Test(expected = Exception::class)
    fun `should return an error when got an error from the repository getting a pokemon by id`() =
        runTest {
            val pokemonId = "fakeId"
            whenever(pokemonRepository.getPokemonById(pokemonId)).thenReturn(flow { throw Exception() })

            getPokemonByIdUseCase(pokemonId).collect()
        }
}
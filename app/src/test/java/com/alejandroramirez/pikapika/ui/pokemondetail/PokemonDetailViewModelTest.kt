package com.alejandroramirez.pikapika.ui.pokemondetail

import androidx.lifecycle.SavedStateHandle
import com.alejandroramirez.pikapika.domain.PokemonObjectMother
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonByIdUseCase
import com.alejandroramirez.pikapika.ui.Arg
import com.alejandroramirez.pikapika.ui.CoroutineTestRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class PokemonDetailViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val stateHandle = mock<SavedStateHandle>()
    private val getPokemonByIdUseCase = mock<GetPokemonByIdUseCase>()
    private lateinit var pokemonDetailViewModel: PokemonDetailViewModel

    @Test
    fun `should set INVALID ID ERROR as state when starting without an id`() = runTest {
        val expectedResult = PokemonDetailViewState(
            error = PokemonDetailErrorType.INVALID_ID,
            isLoading = false
        )

        pokemonDetailViewModel = PokemonDetailViewModel(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            stateHandle = stateHandle,
            getPokemonByIdUseCase = getPokemonByIdUseCase
        )

        assertEquals(pokemonDetailViewModel.state.value, expectedResult)
    }

    @Test
    fun `should set a pokemon with the state when starting by id and got that pokemon`() = runTest {
        val pokemonId = 1
        val pokemon = PokemonObjectMother.createPokemon(id = pokemonId)
        val expectedResult = PokemonDetailViewState(
            pokemon = pokemon,
            isLoading = false
        )
        whenever(stateHandle.get<String>(Arg.POKEMON_ID_ARGUMENT)).thenReturn(pokemonId.toString())
        whenever(getPokemonByIdUseCase(pokemonId.toString())).thenReturn(flowOf(pokemon))

        pokemonDetailViewModel = PokemonDetailViewModel(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            stateHandle = stateHandle,
            getPokemonByIdUseCase = getPokemonByIdUseCase
        )

        assertEquals(pokemonDetailViewModel.state.value, expectedResult)
    }
}
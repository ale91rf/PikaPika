package com.alejandroramirez.pikapika.ui.pokemondetail

import androidx.lifecycle.SavedStateHandle
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonByIdUseCase
import com.alejandroramirez.pikapika.ui.CoroutineTestRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock


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
}
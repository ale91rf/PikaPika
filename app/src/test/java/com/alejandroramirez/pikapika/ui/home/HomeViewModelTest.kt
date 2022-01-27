package com.alejandroramirez.pikapika.ui.home

import com.alejandroramirez.pikapika.domain.PokemonObjectMother
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonsUseCase
import com.alejandroramirez.pikapika.ui.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val getPokemonsUseCase = mock<GetPokemonsUseCase>()
    private lateinit var homeViewModel: HomeViewModel

    @Test
    fun `should get all pokemons when starting`() = runTest {
            val pokemons = listOf(
                PokemonObjectMother.createPokemon()
            )
            val expectedResult = HomeViewState(
                pokemons = pokemons,
                isLoading = false
            )
            whenever(getPokemonsUseCase()).thenReturn(
                flowOf(
                    pokemons
                )
            )

            homeViewModel = HomeViewModel(
                dispatcher = coroutinesTestRule.testDispatcherProvider,
                getPokemonsUseCase = getPokemonsUseCase
            )

            assertEquals(homeViewModel.state.value, expectedResult)
        }
}
package com.alejandroramirez.pikapika.ui.home

import androidx.lifecycle.ViewModel
import com.alejandroramirez.pikapika.DefaultDispatcherProvider
import com.alejandroramirez.pikapika.DispatcherProvider
import com.alejandroramirez.pikapika.domain.model.Pokemon
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel(
    dispatcher: DispatcherProvider = DefaultDispatcherProvider(),
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state
}

data class HomeViewState(
    val pokemons: List<Pokemon> = emptyList(),
    val errorMessage: String? = null
)
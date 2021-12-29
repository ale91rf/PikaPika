package com.alejandroramirez.pikapika.ui.home

import DefaultDispatcherProvider
import DispatcherProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandroramirez.pikapika.domain.model.Pokemon
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeViewModel(
    dispatcher: DispatcherProvider = DefaultDispatcherProvider(),
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {
            getPokemonsUseCase()
                .flowOn(dispatcher.io())
                .catch {
                    //TODO
                }
                .collect { pokemons ->
                    _state.value = HomeViewState(
                        pokemons = pokemons
                    )
                }
        }
    }
}

data class HomeViewState(
    val pokemons: List<Pokemon> = emptyList(),
    val errorMessage: String? = null
)
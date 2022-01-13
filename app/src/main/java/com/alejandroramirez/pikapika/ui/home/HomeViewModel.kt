package com.alejandroramirez.pikapika.ui.home

import com.alejandroramirez.pikapika.di.DispatcherProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandroramirez.pikapika.domain.model.Pokemon
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider,
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
                    _state.value = HomeViewState(
                        error = HomeErrorType.NETWORK,
                        isLoading = false
                    )
                }
                .collect { pokemons ->
                    _state.value = HomeViewState(
                        pokemons = pokemons,
                        isLoading = false
                    )
                }
        }
    }
}

data class HomeViewState(
    val pokemons: List<Pokemon> = emptyList(),
    val error: HomeErrorType? = null,
    val isLoading: Boolean = true
)
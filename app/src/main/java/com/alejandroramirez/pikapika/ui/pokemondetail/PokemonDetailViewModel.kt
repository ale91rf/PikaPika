package com.alejandroramirez.pikapika.ui.pokemondetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandroramirez.pikapika.di.DispatcherProvider
import com.alejandroramirez.pikapika.domain.model.Pokemon
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonByIdUseCase
import com.alejandroramirez.pikapika.ui.Arg.POKEMON_ID_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val stateHandle: SavedStateHandle,
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase
) : ViewModel() {

    private val pokemonId = stateHandle.get<String>(POKEMON_ID_ARGUMENT)
    private val _state = MutableStateFlow(PokemonDetailViewState())
    val state: StateFlow<PokemonDetailViewState>
        get() = _state

    init {
        pokemonId?.let { id ->
            viewModelScope.launch {
                getPokemonByIdUseCase(id)
                    .flowOn(dispatcher.io())
                    .catch { setError(PokemonDetailErrorType.NETWORK) }
                    .collect { pokemon ->
                        _state.value = PokemonDetailViewState(pokemon = pokemon, isLoading = false)
                    }
            }
        } ?: setError(PokemonDetailErrorType.INVALID_ID)
    }

    private fun setError(error: PokemonDetailErrorType) {
        _state.value = PokemonDetailViewState(error = error, isLoading = false)
    }
}

data class PokemonDetailViewState(
    val pokemon: Pokemon? = null,
    val error: PokemonDetailErrorType? = null,
    val isLoading: Boolean = true
)
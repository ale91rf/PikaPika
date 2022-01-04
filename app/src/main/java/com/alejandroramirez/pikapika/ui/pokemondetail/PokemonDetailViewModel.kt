package com.alejandroramirez.pikapika.ui.pokemondetail

import androidx.lifecycle.ViewModel
import com.alejandroramirez.pikapika.di.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider
) : ViewModel()
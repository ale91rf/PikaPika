package com.alejandroramirez.pikapika.di

import com.alejandroramirez.pikapika.domain.PokemonRepository
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonByIdUseCase
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetPokemonsUseCase(
        repository: PokemonRepository
    ) = GetPokemonsUseCase(
        repository
    )

    @Provides
    fun provideGetPokemonByIdUseCase(
        repository: PokemonRepository
    ) = GetPokemonByIdUseCase(
        repository
    )
}
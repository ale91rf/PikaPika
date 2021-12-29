package com.alejandroramirez.pikapika.di

import com.alejandroramirez.pikapika.domain.PokemonRepository
import com.alejandroramirez.pikapika.domain.usecase.GetPokemonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideGetPokemonsUseCase(
        repository: PokemonRepository
    ) = GetPokemonsUseCase(
        repository
    )
}
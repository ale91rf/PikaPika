package com.alejandroramirez.pikapika.di

import com.alejandroramirez.pikapika.domain.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(): PokemonRepository =
        com.alejandroramirez.pikapika.data.PokemonRepository()
}
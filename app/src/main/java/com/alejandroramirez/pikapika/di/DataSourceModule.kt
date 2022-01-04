package com.alejandroramirez.pikapika.di

import com.alejandroramirez.pikapika.data.service.PokemonRetrofitService
import com.alejandroramirez.pikapika.data.datasource.PokemonCloudDataSource
import com.alejandroramirez.pikapika.data.datasource.PokemonRetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideCloudDataSource(
        pokemonRetrofitService: PokemonRetrofitService
    ): PokemonCloudDataSource = PokemonRetrofitDataSource(
        pokemonRetrofitService
    )
}
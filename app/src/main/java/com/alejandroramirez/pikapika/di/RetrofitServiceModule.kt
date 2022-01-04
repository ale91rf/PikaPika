package com.alejandroramirez.pikapika.di

import com.alejandroramirez.pikapika.data.service.PokemonRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitServiceModule {

    @Provides
    @Singleton
    fun providePokemonRetrofitService(
        retrofit: Retrofit
    ): PokemonRetrofitService = retrofit.create(PokemonRetrofitService::class.java)
}
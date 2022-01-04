package com.alejandroramirez.pikapika.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class DispatcherModule {

    @Provides
    fun provideDispatcher(): DispatcherProvider =
        DefaultDispatcherProvider()
}
package com.alejandroramirez.pikapika

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {
    fun io(): CoroutineDispatcher = Dispatchers.IO
}

class DefaultDispatcherProvider :
    DispatcherProvider
package com.example.coroutinesdemo

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import javax.inject.Inject

interface IGetGreetingUseCase {

    suspend operator fun invoke(): String
}

@ViewModelScoped
class GetGreetingUseCase @Inject constructor() : IGetGreetingUseCase {

    override suspend fun invoke(): String {
        delay(500L)
        return "Hello!"
    }
}
package com.example.coroutinesdemo.di

import com.example.coroutinesdemo.GetGreetingUseCase
import com.example.coroutinesdemo.IGetGreetingUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GreetingsModule {

    @Binds
    abstract fun bindIGetGreetingUseCase(
        getGreetingUseCase: GetGreetingUseCase
    ): IGetGreetingUseCase
}
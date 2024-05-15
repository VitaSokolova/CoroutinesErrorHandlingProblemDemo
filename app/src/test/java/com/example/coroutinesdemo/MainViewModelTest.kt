package com.example.coroutinesdemo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(CoroutinesTestExtension::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val stubGreetingsUseCase = object : IGetGreetingUseCase {
        override suspend fun invoke(): String {
            delay(50L)
            throw IllegalArgumentException()
        }
    }

    @Test
    fun loadDataWithTryCatch() = runTest {
        val viewModel = MainViewModel(stubGreetingsUseCase)

        viewModel.loadData()
        advanceUntilIdle()

        assert(viewModel.greeting.value == "Error occurred!")
    }

    @Test
    fun loadDataWithCoroutineExceptionHandler() = runTest {
        val viewModel = MainViewModel(stubGreetingsUseCase)

        viewModel.loadDataWithCoroutineExceptionHandler()
        advanceUntilIdle()

        assert(viewModel.greeting.value == "Error occurred!")
    }
}
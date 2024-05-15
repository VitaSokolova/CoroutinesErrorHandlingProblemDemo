package com.example.coroutinesdemo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGreetingUseCase: IGetGreetingUseCase
) : ViewModel() {

    val greeting = mutableStateOf("")

    fun loadData() {
        viewModelScope.launch {
            try {
                greeting.value = async { getGreetingUseCase() }.await()
            }
            catch (e: CancellationException){
                throw e
            }
            catch (e: Exception) {
                greeting.value = "Error occurred!"
            }
        }
    }

    fun loadDataWithCoroutineExceptionHandler() {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            greeting.value = "Error occurred!"
        }
        viewModelScope.launch(exceptionHandler) {
            greeting.value = async { getGreetingUseCase() }.await()
        }
    }
}
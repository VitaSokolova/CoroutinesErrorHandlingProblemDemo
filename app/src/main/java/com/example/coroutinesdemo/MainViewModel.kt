package com.example.coroutinesdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun loadData() {
        viewModelScope.launch {
            try {
                async { throw IllegalArgumentException() }.await()
            } catch (e: Exception) {
                Log.e("EXAMPLE_TAG", e.message ?: "Error")
            }
        }
    }

    fun loadDataWithCoroutineExceptionHandler() {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            Log.e("EXAMPLE_TAG", exception.message ?: "Error")
        }
        viewModelScope.launch(exceptionHandler) {
            async { throw IllegalArgumentException() }.await()
        }
    }
}
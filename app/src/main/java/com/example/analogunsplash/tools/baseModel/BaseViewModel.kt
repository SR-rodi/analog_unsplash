package com.example.analogunsplash.tools.baseModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.analogunsplash.data.state.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel:ViewModel() {

    protected val _loadState =
        MutableStateFlow(LoadState.LOADING)
    val loadState = _loadState.asStateFlow()

    protected val handler = CoroutineExceptionHandler { _, t ->
        Log.e("Kart","${t.message}")
            _loadState.value = LoadState.ERROR
    }
}
package com.example.analogunsplash.presentation.auth.start

import androidx.lifecycle.viewModelScope
import com.example.analogunsplash.data.state.LoadState
import com.example.analogunsplash.domine.repository.TokenRepository
import com.example.analogunsplash.tools.baseModel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val repository: TokenRepository,
) : BaseViewModel() {

    private val _token = MutableSharedFlow<String>()
    val token = _token.asSharedFlow()

    private var accessToken = PLUG

    init {
        startState()
    }

    private fun startState() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadState.emit(LoadState.START)
        }
    }

    fun createToken(code: String) {
        if (code != PLUG && accessToken != START_REQUEST)
            viewModelScope.launch(Dispatchers.IO) {
                _loadState.emit(LoadState.LOADING)
                accessToken = START_REQUEST
                accessToken = try {
                    repository.getToken(code = code).token
                } catch (t: Exception) {
                    _loadState.emit(LoadState.ERROR.apply { message = t.message.toString() })
                    PLUG
                }
                    _token.emit(accessToken)
                    _loadState.emit(LoadState.SUCCESS)

            }
    }


    companion object {
        const val PLUG = ""
        const val START_REQUEST = "start_request"
    }
}
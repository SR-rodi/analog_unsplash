package com.example.analogunsplash.presentation.auth.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.analogunsplash.data.dto.photo.PhotoItem
import com.example.analogunsplash.data.state.LoadState
import com.example.analogunsplash.domine.repository.TokenRepository
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import com.example.analogunsplash.tools.baseModel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StartAuthorizationViewModel(
    private val repository: TokenRepository,
) : BaseViewModel() {

    private val _token = MutableSharedFlow<String>()
    val token = _token.asSharedFlow()

    private var accessToken = PLUG

    fun createToken(code: String) {
        if (code!= PLUG)
            viewModelScope.launch(Dispatchers.IO) {
                _loadState.emit(LoadState.LOADING)
            accessToken = START_REQUEST
            accessToken = repository.getToken(code = code).token
            if (accessToken != START_REQUEST && accessToken != PLUG) {
                _token.emit(accessToken)
                _loadState.emit(LoadState.SUCCESS)
            }
        }
    }


    companion object {
        const val PLUG = ""
        const val START_REQUEST = "start_request"
    }
}
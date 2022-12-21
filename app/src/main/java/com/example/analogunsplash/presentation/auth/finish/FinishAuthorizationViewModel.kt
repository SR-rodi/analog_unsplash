package com.example.analogunsplash.presentation.auth.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.analogunsplash.data.dto.photo.PhotoItem
import com.example.analogunsplash.domine.repository.TokenRepository
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FinishAuthorizationViewModel(
    private val repository: TokenRepository,
    private val photoRepository: PhotoPagingSourceRepository,
) : ViewModel() {

    private val _token = MutableSharedFlow<String>()
    val token = _token.asSharedFlow()

    private val _listPhoto = MutableStateFlow<PagingData<PhotoItem>>(PagingData.empty())
    val listPhoto = _listPhoto.asStateFlow()

    private var accessToken = PLUG

    fun getToken() = accessToken

    fun createToken(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accessToken = START_REQUEST
            accessToken = repository.getToken(code = code).token
            if (accessToken != START_REQUEST) {
                _token.emit(accessToken)
                getPhoto()
            }
        }
    }

    fun getPhoto() = photoRepository.getFlowPhoto().onEach {
        _listPhoto.value = it
    }.launchIn(viewModelScope)

    companion object {
        const val PLUG = ""
        const val START_REQUEST = "start_request"
    }
}
package com.example.analogunsplash.presentation.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class PhotoViewModel(
    private val photoRepository: PhotoPagingSourceRepository
) : ViewModel() {

    private val _photo = photoRepository.getFlowPhoto().cachedIn(viewModelScope)
    val photo = _photo.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

}
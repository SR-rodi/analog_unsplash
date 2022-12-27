package com.example.analogunsplash.presentation.ribbon

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.data.state.LoadState
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import com.example.analogunsplash.tools.baseModel.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class RibbonViewModel(
    private val photoRepository: PhotoPagingSourceRepository,
) : BaseViewModel() {

    private val query = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getItems() = query.asStateFlow()
        .flatMapLatest { photoRepository.getFlowPhoto(it) }
        .cachedIn(viewModelScope)

    fun setLick(item: TapeItem) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val response = if (item.likedByUser) photoRepository.deleteLick(item.photoId).photo
            else photoRepository.setLick(item.photoId).photo
            val newItem =
                item.copy(likedByUser = response.likedByUser, counterLikes = response.counterLikes)
            photoRepository.updateLikeDB(newItem.toTapeItemEntity())
            _loadState.value = LoadState.SUCCESS
        }
    }

    private var job:Job?=null
    fun setQuery(newText: String, refresh: () -> Unit) {
        job?.cancel()
        job = viewModelScope.launch() {
            delay(500)
            query.value = newText
            refresh()
        }
    }
}
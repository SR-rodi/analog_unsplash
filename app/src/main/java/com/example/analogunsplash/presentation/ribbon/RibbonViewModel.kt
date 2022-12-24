package com.example.analogunsplash.presentation.ribbon

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import com.example.analogunsplash.tools.baseModel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class RibbonViewModel(
    private val photoRepository: PhotoPagingSourceRepository,
) : BaseViewModel() {

    private val query = MutableStateFlow("")

    fun getItems() = photoRepository.getFlowPhoto("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun test() = query.asStateFlow()
        .debounce(500)
        .flatMapLatest {
        photoRepository.getFlowPhoto(it)}
        .cachedIn(viewModelScope)

    fun setLick(item: TapeItem) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val response = if (item.likedByUser) photoRepository.deleteLick(item.photoId).photo
            else photoRepository.setLick(item.photoId).photo
            val newItem = item.copy(likedByUser = response.likedByUser, counterLikes = response.counterLikes)
            photoRepository.updateLikeDB(newItem.toTapeItemEntity())
        }

    }

    fun setQuery(newText: String, function: () -> Unit) {
        query.value = newText
    }
}
package com.example.analogunsplash.presentation.ribbon

import androidx.lifecycle.viewModelScope
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import com.example.analogunsplash.tools.baseModel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RibbonViewModel(
    private val photoRepository: PhotoPagingSourceRepository,
) : BaseViewModel() {

    fun getItems() = photoRepository.getFlowPhoto()

    fun setLick(item: TapeItem) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val newItem = if (item.likedByUser) photoRepository.deleteLick(item.photoId).photo
            else photoRepository.setLick(item.photoId).photo
            photoRepository.updateLikeDB(newItem.toTapeItemEntity())
        }

    }
}
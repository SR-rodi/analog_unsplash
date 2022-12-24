package com.example.analogunsplash.presentation.ribbon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.data.state.LocaleChange
import com.example.analogunsplash.data.state.OnChange
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class RibbonViewModel(
    private val photoRepository: PhotoPagingSourceRepository
) : ViewModel() {

    private val localChange = LocaleChange()
    private val localeChangeFlow = MutableStateFlow(OnChange(localChange))

    var items = photoRepository.getFlowPhoto()
        .cachedIn(viewModelScope)
        .combine(localeChangeFlow, this::merge)
        .cachedIn(viewModelScope)

    fun setLick(item: TapeItem) {
        viewModelScope.launch {
            val newItem = if (item.likedByUser) photoRepository.setLick(item.photoId).photo
            else photoRepository.deleteLick(item.photoId).photo
            setFlag(newItem.toTapeItem())
        }

    }

    private suspend fun setFlag(newItem: TapeItem) {

            val newFlag = newItem.likedByUser
            localChange.isFavorite[newItem.photoId] = newFlag
            localeChangeFlow.emit(OnChange(localChange))

    }

    private fun merge(
        photo: PagingData<TapeItem>,
        localeChange: OnChange<LocaleChange>
    ) = photo.map { item ->
        val localFavorite = localeChange.value.isFavorite[item.photoId]
        val newItem = if (localFavorite != null) item.copy(likedByUser = localFavorite)
        else item
        newItem
    }
}
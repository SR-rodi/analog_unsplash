package com.example.analogunsplash.presentation.ribbon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RibbonViewModel(
    private val photoRepository: PhotoPagingSourceRepository
) : ViewModel() {

    private val localChange = LocaleChange()
    private val localeChangeFlow = MutableStateFlow(OnChange(localChange))

    var items = photoRepository.getFlowPhoto()
        .cachedIn(viewModelScope)
        .combine(localeChangeFlow, transform = this::merge)
        .cachedIn(viewModelScope)

    fun setLick(item: TapeItem) {
        setFlag(item)
    }

    private fun setFlag(item: TapeItem) {

        viewModelScope.launch(Dispatchers.IO) {
            Log.e("Kart", item.photoId)
                val newItem = if (item.likedByUser) photoRepository.setLick(item.photoId)
                else photoRepository.deleteLick(item.photoId)

            val newFlag = !item.likedByUser
            localChange.isFavorite[item.photoId] = newFlag
            localeChangeFlow.emit(OnChange(localChange))

        }
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


class OnChange<T>(val value: T)

class LocaleChange {
    var isFavorite = mutableMapOf<String, Boolean>()
    var isProgress = mutableSetOf<Boolean>()
}
package com.example.analogunsplash.presentation.ribbon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.analogunsplash.data.bd.enity.TapeItemEntity
import com.example.analogunsplash.data.dto.photo.change.LocaleChange
import com.example.analogunsplash.data.dto.photo.change.OnChange
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.data.reposytory.TapeDbRepository
import com.example.analogunsplash.domine.repository.PhotoRepository
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSours
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    fun setLick(item: TapeItem) {
        viewModelScope.launch(Dispatchers.IO) {
            localChange.isProgress[item.photoId] = true
            localeChangeFlow.emit(OnChange(localChange))
            val newItem =
                if (!item.likedByUser) photoRepository.setLick(item.photoId).photo.toTapeItem()
                else photoRepository.deleteLick(item.photoId).photo.toTapeItem()
            delay(700)//тестовый показ прогресс бара
            setNewData(newItem)
        }

    }

    private suspend fun setNewData(newItem: TapeItem) {
        localChange.isFavorite[newItem.photoId] = newItem.likedByUser
        localChange.likesCounter[newItem.photoId] = newItem.counterLikes
        localChange.isProgress[newItem.photoId] = false
        localeChangeFlow.emit(OnChange(localChange))
    }

    private fun merge(
        photo: PagingData<TapeItem>,
        localeChange: OnChange<LocaleChange>,
    ) = photo.map { item ->
        Log.e("Kart",item.photoId)
        val localFavorite = localeChange.value.isFavorite[item.photoId]
        val likesCounter = localeChange.value.likesCounter[item.photoId]
        val process = localeChange.value.isProgress[item.photoId]
        val newItem = if (localFavorite != null)
            item.copy(likedByUser = localFavorite,
                counterLikes = likesCounter!!,
                isLikeProgress = process!!)
        else item
        newItem
    }
}
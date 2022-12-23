package com.example.analogunsplash.presentation.ribbon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.analogunsplash.data.model.ItemInStrip
import com.example.analogunsplash.data.model.UserSmallInfo
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RibbonViewModel(
    private val photoRepository: PhotoPagingSourceRepository
) : ViewModel() {

    private val localChange = LocaleChange()
    private val localeChangeFlow = MutableStateFlow(OnChange(localChange))

    var items = photoRepository.getFlowPhoto()
        .cachedIn(viewModelScope)
        .combine(localeChangeFlow, transform =@RibbonViewModel::merge)
        .cachedIn(viewModelScope)

    fun setLick(item: ItemInStrip) {
        Log.d("Kart", "Start click ")
        setFlag(item)
    }

    private fun setFlag(item: ItemInStrip) {
        viewModelScope.launch {
            val newFlag = !item.likedByUser
            localChange.isFavorite[item.photoId] = newFlag
            localeChangeFlow.emit(OnChange(localChange))


        }
        // combine(items,localeChangeFlow, this@RibbonViewModel::merge).cachedIn(viewModelScope)
    }

}

 fun merge(
    photo: PagingData<ItemInStrip>,
    localeChange: OnChange<LocaleChange>
): PagingData<ItemInStrip> {

        return photo.map { item ->
            Log.d("Kart", "Start 1245645")
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
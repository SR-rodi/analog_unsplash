package com.example.analogunsplash.domine.repository.pagingsours

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.analogunsplash.data.model.ItemInStrip
import com.example.analogunsplash.domine.repository.PhotoRepository
import com.example.analogunsplash.presentation.ribbon.LocaleChange
import com.example.analogunsplash.presentation.ribbon.OnChange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class PhotoPagingSourceRepository(
    private val repository: PhotoRepository,
) {
    fun getFlowPhoto(): Flow<PagingData<ItemInStrip>> {
       return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PhotoPagingSours(repository) }
        ).flow

    }

   suspend fun setLick(id:String) = repository.setLick(id).toItemInStrip()

    fun deleteLick(){}
}
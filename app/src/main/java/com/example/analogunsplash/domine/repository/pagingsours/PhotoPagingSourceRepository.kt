package com.example.analogunsplash.domine.repository.pagingsours

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.analogunsplash.data.dto.photo.PhotoItem
import com.example.analogunsplash.domine.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

class PhotoPagingSourceRepository(
    private val repository: PhotoRepository,
) {
    fun getFlowPhoto(): Flow<PagingData<PhotoItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PhotoPagingSours(repository) }
        ).flow
    }
}
package com.example.analogunsplash.domine.repository.pagingsours

import androidx.paging.*
import com.example.analogunsplash.data.dto.photo.PhotoItem
import com.example.analogunsplash.domine.repository.PhotoRepository

class PhotoPagingSours(
    private val repository: PhotoRepository,
) : PagingSource<Int, PhotoItem>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoItem>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoItem> {
        val page = params.key ?: FIRST_PAGE

        return kotlin.runCatching {
            repository.getPopularPhoto(page).toList()
        }.fold(
            onSuccess = { photoList ->
                LoadResult.Page(
                    data = photoList,
                    prevKey = if (page == 1) null else page - 1,
                    //nextKey = if (a.isEmpty()) null else page + 1
                    nextKey = null // экономим запросы
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}
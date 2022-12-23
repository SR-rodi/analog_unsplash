package com.example.analogunsplash.domine.repository.pagingsours

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.data.model.UserSmallInfo
import com.example.analogunsplash.domine.repository.PhotoRepository
import com.example.analogunsplash.tools.toListTapeItem

class PhotoPagingSours(
    private val repository: PhotoRepository,
) : PagingSource<Int, TapeItem>() {

    override fun getRefreshKey(state: PagingState<Int, TapeItem>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TapeItem> {
        val page = params.key ?: FIRST_PAGE


      val items =  TapeItem(
            "","",100,100,false,15, UserSmallInfo(
                "",""
            ),false,"@asdsdf"
        )

        return kotlin.runCatching {
           // List<TapeItem>(15){items}
            repository.getPopularPhoto(page).toListTapeItem()
        }.fold(
            onSuccess = { photoList ->
                LoadResult.Page(
                    data = photoList,
                    prevKey = if (page == 1) null else page - 1,
                   // nextKey = if (photoList.isEmpty()) null else page + 1
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
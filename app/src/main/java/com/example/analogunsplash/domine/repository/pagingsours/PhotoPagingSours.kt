package com.example.analogunsplash.domine.repository.pagingsours

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.analogunsplash.data.model.ItemInStrip
import com.example.analogunsplash.data.model.UserSmallInfo
import com.example.analogunsplash.domine.repository.PhotoRepository
import com.example.analogunsplash.tools.toListItemsStrip

class PhotoPagingSours(
    private val repository: PhotoRepository,
) : PagingSource<Int, ItemInStrip>() {

    override fun getRefreshKey(state: PagingState<Int, ItemInStrip>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemInStrip> {
        val page = params.key ?: FIRST_PAGE

   /*   val items =  ItemInStrip(
            "","",100,100,false,15, UserSmallInfo(
                "","","",""
            )
        )*/

        return kotlin.runCatching {
           /* List<ItemInStrip>(15){items}*/
            repository.getPopularPhoto(page).toListItemsStrip()
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
package com.example.analogunsplash.data.reposytory

import android.util.Log
import androidx.paging.*
import com.example.analogunsplash.data.bd.enity.TapeItemEntity
import com.example.analogunsplash.domine.repository.PhotoRepository
import com.example.analogunsplash.tools.toListTapeItem
import com.example.analogunsplash.tools.toListTapeItemEntity

@OptIn(ExperimentalPagingApi::class)
class TapeRemoteMediator(
    private val database: TapeDbRepository,
    private val networkRepository: PhotoRepository,
    private val query:String
) : RemoteMediator<Int, TapeItemEntity>() {

    private var pageIndex = 0

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TapeItemEntity>,
    ): MediatorResult {

        Log.e("Kart","Start ${loadType.name}")

        pageIndex = getIndex(loadType) ?: return MediatorResult.Success(true)

        return try {
            val response = if (query==""){
                Log.e("Kart","Start popular")
                networkRepository.getPopularPhoto(pageIndex).toListEntity()
            }
            else {
                Log.e("Kart","Start query")
                networkRepository.searchPhoto(query, pageIndex).results.toListTapeItemEntity()
            }
            if (loadType == LoadType.REFRESH){
                database.clear()
            }

            else database.insertData(response)



            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getIndex(loadType: LoadType): Int? {

        return when (loadType) {
            LoadType.PREPEND -> null
            LoadType.REFRESH -> 1
            LoadType.APPEND -> ++pageIndex
        }
    }
}
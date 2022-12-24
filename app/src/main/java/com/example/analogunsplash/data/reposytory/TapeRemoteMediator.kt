package com.example.analogunsplash.data.reposytory

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.analogunsplash.data.bd.enity.TapeItemEntity
import com.example.analogunsplash.domine.repository.PhotoRepository

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
        var isSearch = false


        pageIndex = getIndex(loadType) ?: return MediatorResult.Success(true)

        return try {
            val response = if (query=="") networkRepository.getPopularPhoto(pageIndex).toListEntity()
            else {
                isSearch = true
                networkRepository.searchPhoto(query, pageIndex).toListEntity()
            }
            if (loadType == LoadType.REFRESH) {
                database.refresh(response)
            }
            else {
                database.insertData(response)
            }


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
package com.example.analogunsplash.domine.repository.pagingsours

import androidx.paging.*
import com.example.analogunsplash.data.bd.enity.TapeItemEntity
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.data.reposytory.TapeDbRepository
import com.example.analogunsplash.data.reposytory.TapeRemoteMediator
import com.example.analogunsplash.domine.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PhotoPagingSourceRepository(
    private val repository: PhotoRepository,
    private val databaseRepository: TapeDbRepository
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getFlowPhoto(query:String): Flow<PagingData<TapeItem>> {
       return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
           remoteMediator = TapeRemoteMediator(databaseRepository,repository,query),
            pagingSourceFactory = { databaseRepository.getPagingData() }
        ).flow.map {
            it.map {entity->
                entity.toTapeItem()
            }
       }
        }

    suspend fun setLick(id: String) = repository.setLick(id)

    suspend fun deleteLick(id: String) = repository.deleteLick(id)

    suspend fun updateLikeDB(entity: TapeItemEntity) = databaseRepository.setLickInDataBase(entity)
    }
package com.example.analogunsplash.data.reposytory

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.analogunsplash.data.bd.enity.TapeItemEntity

interface TapeDbRepository {

    suspend fun insertData(data: List<TapeItemEntity>)

    fun getPagingData(): PagingSource<Int, TapeItemEntity>

    suspend fun clear()

    suspend fun setLickInDataBase(itemEntity: TapeItemEntity)

    suspend fun refresh(data: List<TapeItemEntity>)

}

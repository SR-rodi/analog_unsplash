package com.example.analogunsplash.data.bd

import androidx.paging.PagingSource
import androidx.room.*
import com.example.analogunsplash.data.bd.enity.TapeItemEntity

@Dao
interface TapeItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<TapeItemEntity>)

    @Query("SELECT*FROM tape_item")
    fun getPagingData(): PagingSource<Int, TapeItemEntity>

    @Query("DELETE FROM tape_item")
    suspend fun clear()

    @Transaction
    suspend fun refresh(data: List<TapeItemEntity>){
        clear()
        insertData(data)
    }
}
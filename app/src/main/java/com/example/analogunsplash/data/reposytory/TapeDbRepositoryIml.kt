package com.example.analogunsplash.data.reposytory

import com.example.analogunsplash.data.bd.TapeItemDao
import com.example.analogunsplash.data.bd.enity.TapeItemEntity

class TapeDbRepositoryIml(private val dao: TapeItemDao) : TapeDbRepository {
    override suspend fun insertData(data: List<TapeItemEntity>)  = dao.insertData(data)

    override fun getPagingData() = dao.getPagingData()

    override suspend fun clear() = dao.clear()

    override suspend fun refresh(data: List<TapeItemEntity>) = dao.refresh(data)

    override suspend fun setLickInDataBase(itemEntity: TapeItemEntity) = dao.setLickInDb(itemEntity)
}
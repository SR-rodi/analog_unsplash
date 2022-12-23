package com.example.analogunsplash.data.dto.photo

import com.example.analogunsplash.data.bd.enity.TapeItemEntity

class ResponsePhotoDto : ArrayList<PhotoItemDto>() {

    fun toListEntity(): MutableList<TapeItemEntity> {
        val newList = mutableListOf<TapeItemEntity>()
        this.forEach {
            newList.add(it.toTapeItemEntity())
        }
        return newList
    }
}
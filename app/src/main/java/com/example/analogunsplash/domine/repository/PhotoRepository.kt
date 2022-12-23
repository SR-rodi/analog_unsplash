package com.example.analogunsplash.domine.repository

import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.dto.photo.ResponsePhotoDto

interface PhotoRepository {

    suspend fun getPopularPhoto(page:Int):ResponsePhotoDto

    suspend fun setLick(id:String):PhotoItemDto

    suspend fun deleteLick(id: String):PhotoItemDto
}
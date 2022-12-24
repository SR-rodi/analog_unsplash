package com.example.analogunsplash.domine.repository

import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.dto.photo.ResponsePhotoDto
import com.example.analogunsplash.data.dto.photo.WrapperPhotoDto

interface PhotoRepository {

    suspend fun getPopularPhoto(page:Int):ResponsePhotoDto

    suspend fun setLick(id:String): WrapperPhotoDto

    suspend fun deleteLick(id: String):WrapperPhotoDto

    suspend fun searchPhoto(query:String,page: Int):ResponsePhotoDto
}
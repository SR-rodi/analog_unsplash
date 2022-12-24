package com.example.analogunsplash.domine.repository

import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.dto.photo.WrapperPhotoDto

interface DetailUseCase {
   suspend fun getPhotoByID(id:String):PhotoItemDto
}
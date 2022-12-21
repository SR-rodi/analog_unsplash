package com.example.analogunsplash.domine.repository

import com.example.analogunsplash.data.dto.photo.ResponsePhotoDto

interface PhotoRepository {

    suspend fun getPopularPhoto(page:Int):ResponsePhotoDto
}
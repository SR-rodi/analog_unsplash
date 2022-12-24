package com.example.analogunsplash.domine.repository.impl

import com.example.analogunsplash.data.dto.photo.WrapperPhotoDto
import com.example.analogunsplash.domine.api.PhotoApi
import com.example.analogunsplash.domine.repository.DetailUseCase

class DetailUseCaseImpl(private val photoApi: PhotoApi): DetailUseCase {
    override suspend fun getPhotoByID(id: String) = photoApi.getPhotoByID(id)
}
package com.example.analogunsplash.domine.repository.impl

import com.example.analogunsplash.domine.api.PhotoApi
import com.example.analogunsplash.domine.repository.PhotoRepository

class PhotoRepositoryImp(private val photoApi: PhotoApi) : PhotoRepository {

    override suspend fun getPopularPhoto(page:Int) = photoApi.getPopularPhoto(page)

}
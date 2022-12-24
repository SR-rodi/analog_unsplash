package com.example.analogunsplash.domine.repository.impl

import android.util.Log
import com.example.analogunsplash.data.dto.photo.ResponsePhotoDto
import com.example.analogunsplash.data.dto.photo.WrapperPhotoDto
import com.example.analogunsplash.domine.api.PhotoApi
import com.example.analogunsplash.domine.repository.PhotoRepository

class PhotoRepositoryImp(private val photoApi: PhotoApi) : PhotoRepository {

    override suspend fun getPopularPhoto(page:Int) = photoApi.getPopularPhoto(page)

    override suspend fun setLick(id: String)= photoApi.setLike(id)

    override suspend fun searchPhoto(query: String,page: Int) = photoApi.searchPhotos(query,page)

    override suspend fun deleteLick(id: String) = photoApi.deleteLike(id)


}
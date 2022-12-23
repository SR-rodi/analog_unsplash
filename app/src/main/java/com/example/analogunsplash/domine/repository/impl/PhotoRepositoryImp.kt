package com.example.analogunsplash.domine.repository.impl

import android.util.Log
import com.example.analogunsplash.data.dto.photo.WrapperPhotoDto
import com.example.analogunsplash.domine.api.PhotoApi
import com.example.analogunsplash.domine.repository.PhotoRepository

class PhotoRepositoryImp(private val photoApi: PhotoApi) : PhotoRepository {

    override suspend fun getPopularPhoto(page:Int) = photoApi.getPopularPhoto(page)

    override suspend fun setLick(id: String): WrapperPhotoDto {
       val a = photoApi.setLike(id)
        Log.e("Sarai", "${a.photo.id}")
        return a
    }

    override suspend fun deleteLick(id: String) = photoApi.deleteLike(id)

}
package com.example.analogunsplash.domine.api

import com.example.analogunsplash.data.dto.photo.ResponsePhotoDto
import com.example.analogunsplash.tools.BASE_URL
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PhotoApi {

    @GET("photos")
    suspend fun getPopularPhoto(
        @Query("page") page: Int,
    ): ResponsePhotoDto
}
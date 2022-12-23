package com.example.analogunsplash.domine.api

import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.dto.photo.ResponsePhotoDto
import retrofit2.http.*

interface PhotoApi {

    @GET("photos")
    suspend fun getPopularPhoto(@Query("page") page: Int): ResponsePhotoDto

    @POST("photos/{id}/like")
    suspend fun setLike(@Path("id") id: String):PhotoItemDto

    @DELETE("photos/{id}/like")
    suspend fun deleteLike(@Path("id") id: String):PhotoItemDto
}
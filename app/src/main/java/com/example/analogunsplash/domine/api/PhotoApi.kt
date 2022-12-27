package com.example.analogunsplash.domine.api

import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.dto.photo.ResponsePhotoDto
import com.example.analogunsplash.data.dto.photo.WrapperPhotoDto
import com.example.analogunsplash.data.dto.search.SearchDto
import retrofit2.http.*

interface PhotoApi {

    @GET("photos")
    suspend fun getPopularPhoto(@Query("page") page: Int): ResponsePhotoDto

    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") query: String,@Query("page") page: Int,): SearchDto

    @POST("photos/{id}/like")
    suspend fun setLike(@Path("id") id: String): WrapperPhotoDto

    @GET("photos/{id}")
    suspend fun getPhotoByID(@Path("id") id: String): PhotoItemDto

    @DELETE("photos/{id}/like")
    suspend fun deleteLike(@Path("id") id: String):WrapperPhotoDto
}
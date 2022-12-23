package com.example.analogunsplash.data.dto.photo

import com.example.analogunsplash.data.model.ItemInStrip
import com.google.gson.annotations.SerializedName

class PhotoItemDto(
    @SerializedName("likes") val counterLikes: Int,
    val id: String,
    val alt_description: String,
    val blur_hash: String,
    val urls: Urls,
    val width: Int,
    val height: Int,
    val likedByUser: Boolean,
    val user: UserDto,
) {
    fun toItemInStrip() =
        ItemInStrip(
            id,
            urls.small,
            width,
            height,
            likedByUser,
            counterLikes,
            user.toSmallUserInfo()
        )
}
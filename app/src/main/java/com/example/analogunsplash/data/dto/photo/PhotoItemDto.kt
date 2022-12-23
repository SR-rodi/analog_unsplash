package com.example.analogunsplash.data.dto.photo

import com.example.analogunsplash.data.bd.enity.TapeItemEntity
import com.example.analogunsplash.data.model.TapeItem
import com.google.gson.annotations.SerializedName

class PhotoItemDto(
    @SerializedName("likes") val counterLikes: Int,
    val id: String,
    val alt_description: String,
    val blur_hash: String,
    val urls: Urls,
    val width: Int,
    val height: Int,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    val user: UserDto,
) {
    fun toTapeItem() = TapeItem(
        id,
        urls.small,
        width,
        height,
        likedByUser,
        counterLikes,
        user.toSmallUserInfo(),
        false,
        twitterUsername
    )

    fun toTapeItemEntity() = TapeItemEntity(
        id,
        urls.small,
        likedByUser,
        counterLikes,
        user.userName,
        user.profileImage.small,
        twitterUsername
    )



}

class ProfileImage(
    val small: String,
)
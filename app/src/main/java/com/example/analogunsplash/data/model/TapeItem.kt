package com.example.analogunsplash.data.model

import com.example.analogunsplash.data.bd.enity.TapeItemEntity

data class TapeItem(
    var photoId: String,
    val smallUrls: String,
    val width: Int,
    val height: Int,
    var likedByUser: Boolean,
    var counterLikes: Int,
    val userInfo: UserSmallInfo,
    var isLikeProgress: Boolean = false,
    val userTwitterName: String?,
) {
    fun toTapeItemEntity() = TapeItemEntity(photoId,
        smallUrls,
        likedByUser,
        counterLikes,
        userInfo.userName,
        userInfo.profileImage,
        userTwitterName)
}

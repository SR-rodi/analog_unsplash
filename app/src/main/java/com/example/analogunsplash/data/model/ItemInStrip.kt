package com.example.analogunsplash.data.model

data class ItemInStrip(
    var photoId: String,
    val smallUrls: String,
    val width: Int,
    val height: Int,
    var likedByUser: Boolean,
    var counterLikes: Int,
    val userInfo: UserSmallInfo
)
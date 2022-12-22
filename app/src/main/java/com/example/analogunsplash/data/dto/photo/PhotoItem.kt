package com.example.analogunsplash.data.dto.photo

data class PhotoItem(
    val alt_description: String,
    val blur_hash: String,
    val urls: Urls,
    val width: Int,
    var like:Boolean,
    val height: Int,
)
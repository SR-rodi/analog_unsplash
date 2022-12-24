package com.example.analogunsplash.tools

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.analogUnsplash.R
import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.model.TapeItem

fun List<PhotoItemDto>.toListTapeItem(): List<TapeItem> {
    val newList = mutableListOf<TapeItem>()

    this.forEach{ item->
        newList.add(item.toTapeItem())
    }
    return newList
}

fun ImageView.loadImage(urls:String){
    Glide.with(this)
        .load(urls)
        .error(R.drawable.arcane_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun ImageView.loadImage(imageId:Int){
    Glide.with(this)
        .load(imageId)
        .error(R.drawable.arcane_image)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

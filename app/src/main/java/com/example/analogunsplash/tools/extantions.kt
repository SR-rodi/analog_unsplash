package com.example.analogunsplash.tools

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.analogUnsplash.R
import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.model.ItemInStrip

fun List<PhotoItemDto>.toListItemsStrip(): List<ItemInStrip> {
    val newList = mutableListOf<ItemInStrip>()

    this.forEach{ item->
        newList.add(item.toItemInStrip())
    }
    return newList
}

fun ImageView.loadImage(urls:String){
    Glide.with(this)
        .load(urls)
        .error(R.drawable.arcane_image)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

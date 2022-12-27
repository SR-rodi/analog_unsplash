package com.example.analogunsplash.tools

import android.widget.ImageView
import android.widget.SearchView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.analogUnsplash.R
import com.example.analogunsplash.data.bd.enity.TapeItemEntity
import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.model.TapeItem

fun List<PhotoItemDto>.toListTapeItem(): List<TapeItem> {
    return this.map {
        it.toTapeItem()
    }
}

fun List<PhotoItemDto>.toListTapeItemEntity(): List<TapeItemEntity> {
    return this.map {
        it.toTapeItemEntity()
    }
}

fun ImageView.loadImage(urls: String) {
    Glide.with(this)
        .load(urls)
        .error(R.drawable.arcane_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun ImageView.loadImage(imageId: Int) {
    Glide.with(this)
        .load(imageId)
        .error(R.drawable.arcane_image)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun SearchView.setChangeTextListener(block:(query:String)->Unit){

    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(newText: String): Boolean {
            block(newText)
            return false
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }
    })
}
package com.example.analogunsplash.data.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.analogunsplash.data.dto.photo.PhotoItem

class DiffPhoto : DiffUtil.ItemCallback<PhotoItem>() {

    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem) =
        oldItem.urls.small == newItem.urls.small

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem) =
        oldItem == newItem
}
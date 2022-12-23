package com.example.analogunsplash.data.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.model.ItemInStrip

class DiffPhoto : DiffUtil.ItemCallback<ItemInStrip>() {

    override fun areItemsTheSame(oldItem: ItemInStrip, newItem: ItemInStrip) =
        oldItem.photoId == newItem.photoId

    override fun areContentsTheSame(oldItem: ItemInStrip, newItem: ItemInStrip) =
        oldItem == newItem
}
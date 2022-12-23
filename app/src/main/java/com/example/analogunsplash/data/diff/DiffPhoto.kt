package com.example.analogunsplash.data.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.analogunsplash.data.model.TapeItem

class DiffPhoto : DiffUtil.ItemCallback<TapeItem>() {

    override fun areItemsTheSame(oldItem: TapeItem, newItem: TapeItem) =
        oldItem.photoId == newItem.photoId

    override fun areContentsTheSame(oldItem: TapeItem, newItem: TapeItem) =
        oldItem == newItem
}
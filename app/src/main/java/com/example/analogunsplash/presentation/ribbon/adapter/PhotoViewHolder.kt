package com.example.analogunsplash.presentation.ribbon.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.analogUnsplash.databinding.ItemPhotoBinding
import com.example.analogunsplash.data.model.ItemInStrip
import com.example.analogunsplash.tools.loadImage
import java.lang.Integer.max
import java.lang.Integer.min

class PhotoViewHolder(private val binding: ItemPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemInStrip, onClick: (item: ItemInStrip) -> Unit) {

        binding.image.setOnClickListener { onClick(item) }

        val ratio = max(item.height, item.width) / min(item.height, item.width)
        binding.image.layoutParams.height = binding.image.layoutParams.width * ratio
        binding.image.loadImage(item.smallUrls)

        binding.favorite.isActivated = item.likedByUser
    }
}

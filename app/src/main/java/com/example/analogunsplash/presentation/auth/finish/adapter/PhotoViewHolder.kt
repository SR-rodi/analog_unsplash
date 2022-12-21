package com.example.analogunsplash.presentation.auth.finish.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.analogUnsplash.databinding.ItemPhotoBinding
import com.example.analogunsplash.data.dto.photo.PhotoItem


class PhotoViewHolder(private val binding: ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(item:PhotoItem?){
        if (item!=null) Glide.with(binding.image)
            .load(item.urls.small)
            .into(binding.image)
    }
}


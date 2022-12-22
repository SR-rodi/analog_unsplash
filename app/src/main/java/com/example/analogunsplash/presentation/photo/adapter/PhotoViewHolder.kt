package com.example.analogunsplash.presentation.photo.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.analogUnsplash.R
import com.example.analogUnsplash.databinding.ItemPhotoBinding
import com.example.analogunsplash.data.dto.photo.PhotoItem
import java.lang.Integer.max
import java.lang.Integer.min


class PhotoViewHolder(private val binding: ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(item:PhotoItem?){
        if (item!=null){
            val ratio  = max(item.height,item.width)/min(item.height,item.width)
            binding.image.layoutParams.height =  binding.image.layoutParams.width * ratio
            binding.image.loadImage(item.urls.small_s3)
        }
    }
}

fun ImageView.loadImage(urls:String){
    Glide.with(this)
        .load(urls)
        .placeholder(R.drawable.placeholder)
        .into(this)
}


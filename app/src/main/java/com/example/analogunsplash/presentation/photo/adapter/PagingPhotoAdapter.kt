package com.example.analogunsplash.presentation.photo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.analogUnsplash.databinding.ItemPhotoBinding
import com.example.analogunsplash.data.diff.DiffPhoto
import com.example.analogunsplash.data.dto.photo.PhotoItem


class PagingPhotoAdapter(): PagingDataAdapter<PhotoItem, PhotoViewHolder>(DiffPhoto()) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=  PhotoViewHolder(
        ItemPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )
}
package com.example.analogunsplash.presentation.ribbon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.analogUnsplash.databinding.ItemPhotoBinding
import com.example.analogunsplash.data.diff.DiffPhoto
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.data.state.ItemButton


class PagingPhotoAdapter(
    private val onClick: (itemInStrip: TapeItem, button: ItemButton) -> Unit,
) : PagingDataAdapter<TapeItem, PhotoViewHolder>(DiffPhoto()) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) { clickItem, button -> onClick(clickItem, button) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoViewHolder(
        ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}
package com.example.analogunsplash.presentation.ribbon.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.analogUnsplash.databinding.ItemPhotoBinding
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.tools.loadImage
import java.lang.Integer.max
import java.lang.Integer.min

class PhotoViewHolder(private val binding: ItemPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TapeItem, onClick: (item: TapeItem) -> Unit) {

        binding.image.setOnClickListener { onClick(item) }

        binding.progressBar.isVisible = item.isLikeProgress
        binding.counterLick.text = item.counterLikes.toString()
        binding.favorite.isActivated = item.likedByUser

        binding.image.loadImage(item.smallUrls)
        binding.avatar.loadImage(item.userInfo.profileImage)

        binding.usersName.text = item.userInfo.userName

    }
}

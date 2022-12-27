package com.example.analogunsplash.presentation.ribbon.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.analogUnsplash.databinding.ItemPhotoBinding
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.data.state.ItemButton
import com.example.analogunsplash.tools.loadImage

class PhotoViewHolder(private val binding: ItemPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TapeItem, onClick: (item: TapeItem,button:ItemButton) -> Unit) {

        binding.image.setOnClickListener { onClick(item,ItemButton.IMAGE) }
        binding.description.setOnClickListener { onClick(item,ItemButton.LICK) }

        binding.progressBar.isVisible = item.isLikeProgress
        binding.counterLick.text = item.counterLikes.toString()
        binding.favorite.isActivated = item.likedByUser

        binding.image.loadImage(item.smallUrls)
        binding.avatar.loadImage(item.userInfo.profileImage)

        binding.usersName.text = item.userInfo.userName

    }
}

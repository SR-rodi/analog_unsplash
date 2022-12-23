package com.example.analogunsplash.data.bd.enity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.data.model.UserSmallInfo

@Entity(tableName = "tape_item")
data class TapeItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "photo_id")
    var photoId: String,
    @ColumnInfo(name = "photo_url")
    val smallUrls: String,
    @ColumnInfo(name = "photo_is_like")
    var likedByUser: Boolean,
    @ColumnInfo(name = "photo_counter_like")
    var counterLikes: Int,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "user_avatar")
    val profileImage: String,
    @ColumnInfo(name = "user_twitter_name")
    val twitterUsername:String?
) {
    fun toTapeItem() = TapeItem(photoId, smallUrls, 0, 0, likedByUser, counterLikes,
        UserSmallInfo(userName,profileImage),false,twitterUsername)
}
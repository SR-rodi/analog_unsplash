package com.example.analogunsplash.data.dto.photo

import com.example.analogunsplash.data.model.UserSmallInfo
import com.google.gson.annotations.SerializedName

class UserDto(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("username")
    val userName: String,
    val id: String,
    val name: String,
){
    fun toSmallUserInfo()=UserSmallInfo(firstName,lastName,userName,id)
}
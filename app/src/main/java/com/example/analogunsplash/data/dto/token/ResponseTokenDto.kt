package com.example.analogunsplash.data.dto.token

import com.google.gson.annotations.SerializedName

class ResponseTokenDto(
    @SerializedName("access_token") val token: String,
    @SerializedName("token_type") val token_type: String,
    @SerializedName("created_at") val created: Long,
    val scope: String,
)
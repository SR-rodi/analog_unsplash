package com.example.analogunsplash.domine.api

import com.example.analogunsplash.tools.CLIENT_ID
import com.example.analogunsplash.tools.REDIRECT_URI
import com.example.analogunsplash.data.dto.token.ResponseTokenDto
import com.example.analogunsplash.tools.SECRET_KEY
import retrofit2.http.POST
import retrofit2.http.Query

interface TokenApi {

    @POST("https://unsplash.com/oauth/token")
    suspend fun getToken(
        @Query("code") code: String,
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = SECRET_KEY,
        @Query("redirect_uri") redirectUri: String = REDIRECT_URI,
        @Query("grant_type") grantType: String = "authorization_code",
    ): ResponseTokenDto

}
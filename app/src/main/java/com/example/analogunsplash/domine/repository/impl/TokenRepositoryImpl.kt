package com.example.analogunsplash.domine.repository.impl


import com.example.analogunsplash.data.dto.token.ResponseTokenDto
import com.example.analogunsplash.domine.api.TokenApi
import com.example.analogunsplash.domine.repository.TokenRepository

class TokenRepositoryImpl(
    private val tokenApi: TokenApi,
) : TokenRepository {

    override suspend fun getToken(code: String): ResponseTokenDto {
        val token = tokenApi.getToken(code)
        return token
    }
}
package com.example.analogunsplash.domine.repository

import com.example.analogunsplash.data.dto.token.ResponseTokenDto

interface TokenRepository {
  suspend  fun getToken(code:String): ResponseTokenDto
}
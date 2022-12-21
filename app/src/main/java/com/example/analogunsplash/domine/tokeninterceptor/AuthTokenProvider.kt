package com.example.analogunsplash.domine.tokeninterceptor

import android.content.Context

class AuthTokenProvider(private val context: Context) {

    fun getToken(): String? {
        val pref = context.getSharedPreferences("Pref_Token", Context.MODE_PRIVATE)

        return pref.getString("Token", "")
    }

}
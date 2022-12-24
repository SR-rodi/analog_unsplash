package com.example.analogunsplash.di

import com.example.analogunsplash.domine.api.PhotoApi
import com.example.analogunsplash.domine.api.TokenApi
import com.example.analogunsplash.domine.tokeninterceptor.AuthInterceptor
import com.example.analogunsplash.domine.tokeninterceptor.AuthTokenProvider
import com.example.analogunsplash.tools.BASE_URL
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val retrofitModule = module {

    single { AuthTokenProvider(get()) }

    factory { AuthInterceptor(get()) }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    single(named("retrofit")) {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<TokenApi> {
        get<Retrofit>(named("retrofit")).create()
    }

    single<PhotoApi> {
        get<Retrofit>(named("retrofit")).create()
    }
}
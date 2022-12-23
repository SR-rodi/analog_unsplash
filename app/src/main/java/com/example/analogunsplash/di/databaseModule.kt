package com.example.analogunsplash.di

import androidx.room.Room
import com.example.analogunsplash.data.bd.AppDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module {

       single(named("UnsplashDatabase")) {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "UnsplashDatabase"
        ).build()
    }

    single {
        get<AppDatabase>(named("UnsplashDatabase")).getItemStripDao()
    }

}
package com.example.analogunsplash.app

import android.app.Application
import com.example.analogunsplash.di.databaseModule
import com.example.analogunsplash.di.repositoryModule
import com.example.analogunsplash.di.retrofitModule
import com.example.analogunsplash.di.viewModelModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    retrofitModule,
                    viewModelModel,
                    repositoryModule,
                    databaseModule
                )
            )
        }

    }
}
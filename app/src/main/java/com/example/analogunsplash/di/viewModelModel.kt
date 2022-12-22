package com.example.analogunsplash.di

import com.example.analogunsplash.presentation.auth.start.AuthorizationViewModel
import com.example.analogunsplash.presentation.photo.PhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModel= module{

    viewModel {
        AuthorizationViewModel(get())
    }

    viewModel {
        PhotoViewModel(get())
    }

}
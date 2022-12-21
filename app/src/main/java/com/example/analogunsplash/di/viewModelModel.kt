package com.example.analogunsplash.di

import com.example.analogunsplash.presentation.auth.finish.FinishAuthorizationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModel= module{

    viewModel {
        FinishAuthorizationViewModel(get(),get())
    }

}
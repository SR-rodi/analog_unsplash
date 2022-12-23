package com.example.analogunsplash.di

import com.example.analogunsplash.data.reposytory.TapeDbRepository
import com.example.analogunsplash.data.reposytory.TapeDbRepositoryIml
import com.example.analogunsplash.data.reposytory.TapeRemoteMediator
import com.example.analogunsplash.domine.repository.PhotoRepository
import com.example.analogunsplash.domine.repository.TokenRepository
import com.example.analogunsplash.domine.repository.impl.PhotoRepositoryImp
import com.example.analogunsplash.domine.repository.impl.TokenRepositoryImpl
import com.example.analogunsplash.domine.repository.pagingsours.PhotoPagingSourceRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TokenRepository> { TokenRepositoryImpl(get()) }
    single<PhotoRepository> { PhotoRepositoryImp(get()) }

    single { PhotoPagingSourceRepository(get(),get()) }
    single<TapeDbRepository> { TapeDbRepositoryIml(get()) }
   // single { TapeRemoteMediator(get(),get()) }

}
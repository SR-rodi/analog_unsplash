package com.example.analogunsplash.data.state

class OnChange<T>(val value: T)

class LocaleChange {
    var isFavorite = mutableMapOf<String, Boolean>()
    var isProgress = mutableSetOf<Boolean>()
}
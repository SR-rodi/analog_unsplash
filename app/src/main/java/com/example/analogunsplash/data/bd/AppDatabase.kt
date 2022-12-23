package com.example.analogunsplash.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.analogunsplash.data.bd.enity.TapeItemEntity

@Database(entities = [TapeItemEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getItemStripDao():TapeItemDao

}
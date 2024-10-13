package com.example.contentproviderapp.room_database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object RoomDatabaseHelper {
    const val TABLE_NAME = "person_new"
    fun getDatabase(context: Context?): AppDatabase? {
        return context?.let {
            Room.databaseBuilder(
                it, AppDatabase::class.java, "cprovider_room_db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}
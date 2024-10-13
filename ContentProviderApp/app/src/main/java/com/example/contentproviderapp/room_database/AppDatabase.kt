package com.example.contentproviderapp.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contentproviderapp.room_database.dao.PersonDao
import com.example.contentproviderapp.room_database.entitiy.Person

@Database(entities = [Person::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPersonDao(): PersonDao
}
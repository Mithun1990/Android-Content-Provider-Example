package com.example.contentproviderapp.room_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.example.contentproviderapp.room_database.entitiy.Person

@Dao
interface PersonDao {
    @Insert
    fun insert(person: Person)

    @Update
    fun update(person: Person)


}
package com.example.contentproviderapp.room_database.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.contentproviderapp.room_database.RoomDatabaseHelper
import com.example.contentproviderapp.room_database.entitiy.Person

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Person): Long

    @Update
    fun update(person: Person): Int

    @RawQuery
    fun query(query: SimpleSQLiteQuery): Cursor?

    @Query("Delete from ${RoomDatabaseHelper.TABLE_NAME} where id = :id")
    fun delete(id: String): Int

}
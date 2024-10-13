package com.example.contentproviderapp.room_database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contentproviderapp.room_database.RoomDatabaseHelper

@Entity(tableName = RoomDatabaseHelper.TABLE_NAME)
data class Person(
    @ColumnInfo("id") @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("age") val age: Int,
    @ColumnInfo("address") val address: String
)
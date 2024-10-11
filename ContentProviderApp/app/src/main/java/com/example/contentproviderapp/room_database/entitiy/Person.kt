package com.example.contentproviderapp.room_database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table")
data class Person(
    @ColumnInfo("_id") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("_name") val name: String, @ColumnInfo("age") val age: Int,
    @ColumnInfo("_address") val address: String
)
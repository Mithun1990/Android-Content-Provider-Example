package com.example.contentproviderapp.sqlite_database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, version) {
    companion object {
        val DB_NAME = "cprovider_app"
        val version = 1
        val TABLE_NAME = "student"
        val ID = "id"
        val NAME = "name"
        val PHONE = "phone"
        val ADDRESS = "address"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            it.execSQL(
                "create table $TABLE_NAME " +
                        "($ID integer primary key autoincrement, $NAME text, $PHONE text, $ADDRESS text)"
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
            db?.apply {
                execSQL("drop table if exists $TABLE_NAME")
                onCreate(db)
            }
        }
    }

    fun insertStudent(name: String, phone: String, address: String): Long {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put(NAME, name)
            put(PHONE, phone)
            put(ADDRESS, address)
        }
        return db.insert(TABLE_NAME, null, cv)
    }

    fun insertStudent(contentValues: ContentValues?): Long {
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun updateStudent(id: Int, name: String, phone: String, address: String): Boolean {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put(NAME, name)
            put(PHONE, phone)
            put(ADDRESS, address)
        }
        return db.update(TABLE_NAME, cv, "$ID = ?", arrayOf("$id")) > 0
    }

    fun getStudent(id: Int): Student? {
        val db = readableDatabase
        val cursor = db.rawQuery("select * from $TABLE_NAME where $ID=$id", null)
        cursor.moveToFirst()
        return convertToStudent(cursor)
    }

    fun getStudent(
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = readableDatabase
        val cursor =
            db.query("$TABLE_NAME", projection, selection, selectionArgs, null, null, sortOrder)
        return cursor
    }

    fun getAllStudents(): List<Student?> {
        val db = readableDatabase
        val cursor = db.rawQuery("select * from $TABLE_NAME", null)
        val list = mutableListOf<Student?>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            list.add(convertToStudent(cursor))
            cursor.moveToNext()
        }
        return list
    }


    private fun convertToStudent(cursor: Cursor): Student? {
        return try {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val phone = cursor.getString(2)
            val address = cursor.getString(3)
            Student(id, name, phone, address)
        } catch (e: Exception) {
            return null
        }
    }

}
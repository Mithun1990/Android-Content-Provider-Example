package com.example.contentproviderapp.room_database

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.contentproviderapp.room_database.entitiy.Person

class PersonProvider : ContentProvider() {

    val db by lazy { RoomDatabaseHelper.getDatabase(context ?: null) }
    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        db?.apply {
            val sql = SQLiteQueryBuilder.buildQueryString(
                false, RoomDatabaseHelper.TABLE_NAME,
                projection, selection, null, null, sortOrder, null
            )
            val cursor = getPersonDao().query(SimpleSQLiteQuery(query = sql))
            cursor?.setNotificationUri(context?.contentResolver, uri)
            return cursor
        }
        return null
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        db?.apply {
            values?.apply {
                val person = Person(
                    name = get("name") as String,
                    age = get("age") as Int,
                    address = get("address") as String
                )
                val id = getPersonDao().insert(person)
                val itemUri = ContentUris.withAppendedId(PersonContracts.URI, id)
                context?.contentResolver?.notifyChange(uri, null)
                return itemUri
            }
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        db?.apply {
            selectionArgs?.get(0)?.let {
                val ret = getPersonDao().delete(it)
                context?.contentResolver?.notifyChange(uri, null)
                return ret
            }
        }
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        db?.apply {
            values?.apply {
                val person = Person(
                    id = get("id") as Int,
                    name = get("name") as String,
                    age = get("age") as Int,
                    address = get("address") as String
                )
                val ret = getPersonDao().update(person)
                context?.contentResolver?.notifyChange(uri, null)
                return ret
            }
        }
        return 0
    }
}
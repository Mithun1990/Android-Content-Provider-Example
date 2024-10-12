package com.example.contentproviderapp.sqlite_database

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class StudentProvider : ContentProvider() {

    private val dbHelper by lazy { DbHelper(context) }

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
        val cursor = dbHelper.getStudent(projection, selection, selectionArgs, sortOrder)
        cursor?.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String? = null
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = dbHelper.insertStudent(values)
        val itemUri = ContentUris.withAppendedId(StudentContracts.URI, id)
        context?.apply {
            contentResolver.notifyChange(itemUri, null)
        }
        return itemUri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val ret = dbHelper.deleteStudent(selection, selectionArgs)
        context?.apply { contentResolver.notifyChange(uri, null) }
        return ret
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val ret = dbHelper.updateStudent(values, selection, selectionArgs)
        context?.apply { contentResolver.notifyChange(uri, null) }
        return ret
    }
}
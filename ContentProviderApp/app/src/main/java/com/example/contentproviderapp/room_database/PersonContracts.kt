package com.example.contentproviderapp.room_database

import android.net.Uri
import com.example.contentproviderapp.sqlite_database.StudentContracts

object PersonContracts {
    val AUTHORITY = "com.cprovider.app.provider.room"
    val URI = Uri.parse("contents://${AUTHORITY}/person")

}
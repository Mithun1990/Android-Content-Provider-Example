package com.example.contentproviderapp.sqlite_database

import android.net.Uri
import java.net.URI

object StudentContracts {
    val AUTHORITY = "com.cprovider.app.provider"
    val URI = Uri.parse("content://$AUTHORITY/student")
}
package com.example.contentconsumerapp.room_cprovider

import android.net.Uri
import com.example.contentconsumerapp.sqlite_cprovider.StudentContracts
import java.net.URI

object PersonContracts {
    val AUTHORITY = "com.cprovider.app.provider.room"
    val URI = Uri.parse("content://${AUTHORITY}/person")
}
package com.example.contentconsumerapp.sqlite_cprovider

import android.net.Uri
import java.net.URI

object StudentContracts {
    val AUTHORITY = "com.cprovider.app.provider"
    val URI = Uri.parse("content://$AUTHORITY/student")
}
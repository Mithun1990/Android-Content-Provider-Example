package com.example.contentconsumerapp.sqlite_cprovider

import android.content.Context

class StudentConsumer(private val context: Context) {

    fun queryStudentData(): List<Student> {
        val uri = StudentContracts.URI
        val cursor = context?.contentResolver?.query(uri, null, null, null, null)
        val list = mutableListOf<Student>()
        cursor?.apply {
            moveToFirst()
            while (!isAfterLast) {
                val id = getInt(0)
                val name = getString(1)
                val phone = getString(2)
                val address = getString(3)
                list.add(Student(id, name, phone, address))
                moveToNext()
            }
        }
        return list
    }
}
package com.example.contentconsumerapp.sqlite_cprovider

import android.content.ContentValues
import android.content.Context
import android.net.Uri

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

    fun insertData(student: Student): Uri? {
        val cv = ContentValues().apply {
            put("name", student.name)
            put("phone", student.phone)
            put("address", student.address)
        }
        val uri = StudentContracts.URI
        val ret = context?.contentResolver?.insert(uri, cv)
        return ret ?: null
    }

    fun updateData(id: Int, student: Student): Int? {
        val uri = StudentContracts.URI
        val cv = ContentValues().apply {
            put("name", student.name)
            put("phone", student.phone)
            put("address", student.address)
        }

        val ret = context?.contentResolver?.update(uri, cv, "id = ?", arrayOf(id.toString()))
        return ret ?: null
    }

    fun deleteData(id: Int): Int? {
        val uri = StudentContracts.URI
        val ret = context?.contentResolver?.delete(uri, "id = ?", arrayOf(id.toString()))
        return ret
    }
}
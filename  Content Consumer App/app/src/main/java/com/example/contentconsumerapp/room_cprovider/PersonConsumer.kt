package com.example.contentconsumerapp.room_cprovider

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import com.example.contentconsumerapp.sqlite_cprovider.Student
import com.example.contentconsumerapp.sqlite_cprovider.StudentContracts

class PersonConsumer(private val context: Context) {

    fun getData(): List<Person> {
        val list = mutableListOf<Person>()
        context.contentResolver.apply {
            val uri = PersonContracts.URI
            val cursor = query(uri, null, null, null, null)
            cursor?.apply {
                moveToFirst()
                while (!isAfterLast) {
                    val person = Person(
                        id = getInt(0),
                        name = getString(1),
                        age = getInt(2),
                        address = getString(3)
                    )
                    list.add(person)
                    moveToNext()
                }
            }
        }
        return list
    }

    fun insertData(person: Person): Uri? {
        val cv = ContentValues().apply {
            put("name", person.name)
            put("age", person.age)
            put("address", person.address)
        }
        val uri = PersonContracts.URI
        val ret = context?.contentResolver?.insert(uri, cv)
        return ret ?: null
    }

    fun updateData(id: Int, person: Person): Int? {
        val uri = PersonContracts.URI
        val cv = ContentValues().apply {
            put("id", person.id)
            put("name", person.name)
            put("age", person.age)
            put("address", person.address)
        }
        val ret = context?.contentResolver?.update(uri, cv, "id = ?", arrayOf(id.toString()))
        return ret ?: null
    }

    fun deleteData(id: Int): Int? {
        val uri = PersonContracts.URI
        val ret = context?.contentResolver?.delete(uri, "id = ?", arrayOf(id.toString()))
        return ret
    }
}
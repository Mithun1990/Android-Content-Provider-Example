package com.example.contentproviderapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contentproviderapp.sqlite_database.DbHelper

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textView = findViewById(R.id.textView)
        val dbHelper = DbHelper(this)

//        val id1 = dbHelper.insertStudent("Naim ", "0100", "Dhaa")
//        val id2 = dbHelper.insertStudent("Mithun ", "0122", "Mymensingh")
//        val id3 = dbHelper.insertStudent("Naim Test", "0133", "Dhaka")
//        val id4 = dbHelper.insertStudent("Mithun Test", "0144", "Mymensingh")
//        textView.text = "Data inserted of id: $id1 $id2 $id3 $id4"
    }
}
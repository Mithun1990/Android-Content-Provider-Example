package com.example.contentconsumerapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contentconsumerapp.sqlite_cprovider.Student
import com.example.contentconsumerapp.sqlite_cprovider.StudentConsumer

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var btnGetData: Button
    lateinit var btnInsertData: Button
    lateinit var btnUpdateData: Button
    lateinit var btnDeleteData: Button
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
        btnGetData = findViewById(R.id.btnGetData)
        btnInsertData = findViewById(R.id.btnInsertData)
        btnUpdateData = findViewById(R.id.btnUpdateData)
        btnDeleteData = findViewById(R.id.btnDeleteData)

        val studentConsumer = StudentConsumer(this)

        btnGetData.setOnClickListener {
            val studentConsumer = StudentConsumer(this)
            println("Consumer data ${studentConsumer.queryStudentData()}")
            textView.text = "Consumer data ${studentConsumer.queryStudentData()}"
        }
        btnInsertData.setOnClickListener {
            val student = Student(-1, "Forhad", "123445", "Canada")
            val uri = studentConsumer.insertData(student);
            println("Inserted uri $uri")
        }

        btnUpdateData.setOnClickListener {
            val student = Student(-1, "Forhad Naim", "123445", "Sweden")
            val uri = studentConsumer.updateData(9, student);
            println("Updated  $uri")
        }
        btnDeleteData.setOnClickListener {
            val uri = studentConsumer.deleteData(1);
            println("Deleted  $uri")
        }
    }
}
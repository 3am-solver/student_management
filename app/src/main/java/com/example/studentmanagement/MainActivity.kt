package com.example.studentmanagement

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.models.Student

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting all fields
        val studentId = findViewById<EditText>(R.id.etStudentID)
        val studentName = findViewById<EditText>(R.id.etStudentName)
        val studentAge = findViewById<EditText>(R.id.etStudentAge)
        val studentAddress = findViewById<EditText>(R.id.etStudentAddress)

        // getting all buttons
        val btnInsert = findViewById<Button>(R.id.btnAdd)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnViewAll = findViewById<Button>(R.id.btnViewAll)

        // Moving to ViewAllStudents activity
        btnViewAll.setOnClickListener {
            val intent = android.content.Intent(this, ViewAllStudentsActivity::class.java)
            startActivity(intent)
        }

        // Inserting a student
        btnInsert.setOnClickListener {
            val student = Student(
                studentId.text.toString().toInt(),
                studentName.text.toString(),
                studentAge.text.toString().toInt(),
                studentAddress.text.toString()
            )
            student.insertStudent()
            Toast.makeText(this, "Student inserted successfully", Toast.LENGTH_SHORT).show()
        }
    }

    // configure database
    private fun configureDatabase(): SQLiteDatabase {
        val database = openOrCreateDatabase("StudentManagement", MODE_PRIVATE, null)
        database.execSQL("CREATE TABLE IF NOT EXISTS student(studentID INTEGER PRIMARY KEY AUTOINCREMENT, studentName VARCHAR, studentAge INTEGER, studentAddress VARCHAR)")
        return database
    }
}
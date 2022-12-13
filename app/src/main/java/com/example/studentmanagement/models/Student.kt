package com.example.studentmanagement.models

import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.*

class Student(
    private val studentID: Int,
    private val studentName: String,
    private val studentAge: Int,
    private val studentAddress: String
){
    // Configuring Database
    private val database = Connection.configureDatabase()

    // Function insert student into database
    fun insertStudent(){
        database.execSQL("INSERT INTO student VALUES($studentID, '$studentName', $studentAge, '$studentAddress')")
    }

    // Function update student into database
    fun updateStudent(studentID: Int, Arr: Array<String>) {
        database.execSQL("UPDATE student SET studentName = ${Arr[0]}, studentAge = ${Arr[1]}, studentAddress = ${Arr[2]} WHERE studentID = $studentID")
    }

    // Function delete student from database
    fun deleteStudent(studentID: Int) {
        val query = "DELETE FROM student WHERE studentID = '$studentID'"
        database.execSQL(query)
    }

    // Function get student from database
    fun getStudent(studentID: Int): Array<String?> {
        val query = "SELECT * FROM student WHERE studentID = '$studentID'"
        val cursor = database.rawQuery(query, null)
        cursor.moveToFirst()
        cursor.close()
        return arrayOf<String?>(
            cursor.getString(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
    }

    // Function get all student from database
    fun getAllStudent(): ArrayList<Student> {
        val query = "SELECT * FROM student"
        val cursor = database.rawQuery(query, null)
        val listStudent = ArrayList<Student>()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                listStudent.add(
                    Student(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return listStudent
    }
}
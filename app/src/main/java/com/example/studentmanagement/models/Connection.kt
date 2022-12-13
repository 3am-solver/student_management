package com.example.studentmanagement.models

import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase

class Connection {
    companion object {
        fun configureDatabase(): SQLiteDatabase {
            val database = openOrCreateDatabase("student.db", MODE_PRIVATE, null)
            database.execSQL("CREATE TABLE IF NOT EXISTS student(studentID INTEGER PRIMARY KEY, studentName VARCHAR(50), studentAge INTEGER, studentAddress VARCHAR(50))")
            return database
        }
    }
}
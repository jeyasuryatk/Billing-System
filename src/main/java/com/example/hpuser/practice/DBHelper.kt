package com.example.hpuser.practice

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) : SQLiteOpenHelper(context, "StudentDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Student(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, mark INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Student")
        onCreate(db)
    }

    fun insert(name: String, mark: Int) : Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("mark", mark)
        val result = db.insert("Student", null, values)
        return result != -1L
    }

    fun getDetails() : String {
        val db = this.readableDatabase
        val result = StringBuilder()
        val cursor = db.rawQuery("SELECT * FROM Student", null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val mark = cursor.getInt(2)

            result.append(id)
            result.append(name)
            result.append(mark)
            result.append("\n")
        }
        cursor.close()
        return result.toString()
    }

    fun update(id: Int, name: String, mark: Int) : Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put("name", name)
        values.put("mark", mark)

        val result = db.update("Student", values, "id=?", arrayOf(id.toString()))

        return result > 0
    }

    fun delete(id: Int) : Boolean {
        val db = this.writableDatabase
        val result = db.delete("Student", "id=?", arrayOf(id.toString()))
        return result > 0
    }

}
package com.example.hpuser.practice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = DBHelper(this)

        val etId = findViewById<EditText>(R.id.etId)
        val etName = findViewById<EditText>(R.id.etName)
        val etMarks = findViewById<EditText>(R.id.etMarks)
        val btnInsert = findViewById<Button>(R.id.btnInsert)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        btnInsert.setOnClickListener {
            val name = etName.text.toString()
            val marks = etMarks.text.toString().toIntOrNull()

            if (name.isNotBlank() && marks != null) {
                val id = db.insert(name, marks)
                Toast.makeText(this, if (id) "Inserted ID" else "Failed", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Enter valid data", Toast.LENGTH_SHORT).show()
            }
        }

        btnView.setOnClickListener {
            val data = db.getDetails()
            Toast.makeText(this, data.ifBlank { "No Data Found" }, Toast.LENGTH_LONG).show()
        }

        btnUpdate.setOnClickListener {
            val id = etId.text.toString().toIntOrNull()
            val name = etName.text.toString()
            val marks = etMarks.text.toString().toIntOrNull()

            if (id != null && name.isNotBlank() && marks != null) {
                val updated = db.update(id, name, marks)
                Toast.makeText(this, if (updated) "Updated Successfully" else "No Record Found to Update", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter valid ID, Name, and Marks", Toast.LENGTH_SHORT).show()
            }
        }


        btnDelete.setOnClickListener {
            val id = etId.text.toString().toIntOrNull()

            if (id != null) {
                val deleted = db.delete(id)
                Toast.makeText(this, if (deleted) "Deleted Successfully" else "No Record Found to Delete", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a valid ID", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
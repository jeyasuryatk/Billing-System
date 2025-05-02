package com.example.hpuser.practice

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private val CHANNEL_ID = "channel_id"
    private val NOTIFICATION_ID = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = intent
        val name = intent.getStringExtra("name")

        Toast.makeText(this, "$name", Toast.LENGTH_SHORT).show()

        val btn = findViewById<Button>(R.id.button3)

        btn.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.mycontextmenu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.otp -> {
                        Toast.makeText(this, "OTP Received", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.message -> {
                        Toast.makeText(this, "Messaged", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()

        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("Do you want to continue ?")
        builder.setPositiveButton("Yes") {
            dialog,_ -> dialog.dismiss()
        }

        builder.setNegativeButton("No") {
                dialog, _ -> dialog.dismiss()
        }

        builder.show()

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Handle the selected date
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                Toast.makeText(this, selectedDate, Toast.LENGTH_LONG).show()
            },
            2025, // Default year
            3,    // Default month (April is 3 because months are 0-indexed)
            10    // Default day
        )
        datePickerDialog.show()

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                // Handle the selected time
                val selectedTime = "$hourOfDay:$minute"
            },
            12, // Default hour
            0,  // Default minute
            true // Use 24-hour format
        )
        timePickerDialog.show()

        val btn3 = findViewById<Button>(R.id.button7)
        btn3.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }


    }


}
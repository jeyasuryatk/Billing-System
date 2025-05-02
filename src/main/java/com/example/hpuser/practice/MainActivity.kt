package com.example.hpuser.practice

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val v = findViewById<View>(R.id.view)
        registerForContextMenu(v)

        val popupBtn = findViewById<Button>(R.id.button)

        popupBtn.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.mypopupmenu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->

                when (item.itemId) {
                    R.id.otpid -> {
                        Toast.makeText(this, "asd", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }

            }
            popupMenu.show()

        }
        val btn = findViewById<Button>(R.id.button2)

        val sms = SmsManager.getDefault()
        sms.sendTextMessage("8608677441", null, "Sasd", null, null)

        btn.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("name","llll")
            startActivity(intent)
        }


        val geocoder = Geocoder(this)
        val addressList = geocoder.getFromLocationName("Madurai, Tamil Nadu",1)
        val locbtn = findViewById<Button>(R.id.button)
        locbtn.setOnClickListener {
            if(addressList != null){
            val address = addressList[0]
            val lat = address.latitude
            val lon = address.longitude
            Toast.makeText(this,"$lon /n $lat",Toast.LENGTH_SHORT).show()
            }

        }


        val reverseBtn = findViewById<Button>(R.id.button4)

        val reverseAddressList = geocoder.getFromLocation(9.94, 78.119, 1)


        reverseBtn.setOnClickListener {
            if (reverseAddressList != null) {
                val address = reverseAddressList[0]
                val name = address.getAddressLine(0)
                Toast.makeText(this, "$name", Toast.LENGTH_SHORT).show()
            }
        }
        val nameText = findViewById<EditText>(R.id.editTextText)
        val nextBtn = findViewById<Button>(R.id.button5)
        nextBtn.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_settings -> {
                Toast.makeText(this,"Clicked Settings!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_about -> {
                Toast.makeText(this, "C", Toast.LENGTH_SHORT).show()
                true

            }
            else -> false
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.mycontextmenu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.otp -> {
                Toast.makeText(this, "OTP sent!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }

    }


}
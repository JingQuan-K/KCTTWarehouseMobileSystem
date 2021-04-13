package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //test

        temp_btn.setOnClickListener {
            val intent = Intent(this, ReceiveMaterialsScanner::class.java)
            startActivity(intent)
        }

        map_btn.setOnClickListener {
            val intent = Intent(this, WarehouseMap::class.java)
            startActivity(intent)
        }

        //Display Toast if successfully placed material to rack
        val intent = intent
        intent.extras
        if (intent.hasExtra(PlaceToRackScanner.RACK_ID))
        {
            val rackId = intent?.extras?.getString(PlaceToRackScanner.RACK_ID).toString()
            val toastMsg = "Successfully Added to Rack $rackId"
            val toast = Toast.makeText(applicationContext, toastMsg, Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
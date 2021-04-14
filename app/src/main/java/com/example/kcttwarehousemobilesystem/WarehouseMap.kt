package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WarehouseMap : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warehouse_map)

        //Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Warehouse Map"
        actionBar.setDisplayHomeAsUpEnabled(true)

    }


}
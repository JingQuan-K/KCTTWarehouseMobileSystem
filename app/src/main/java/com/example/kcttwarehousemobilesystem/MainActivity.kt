package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

//hi this is a comment by kjq
//lol

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView(R.layout.activity_register_material)

        val btn = findViewById<Button>(R.id.button)
            btn.setOnClickListener {
            intent = Intent(this, com.example.kcttwarehousemobilesystem.RegisterMaterial::class.java)
            startActivity(intent)
        }
    }
}
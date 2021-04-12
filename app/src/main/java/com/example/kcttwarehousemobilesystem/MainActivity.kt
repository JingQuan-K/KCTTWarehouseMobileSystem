package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    //private lateinit var toolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.register)
        //setContentView(R.layout.login)
        //setContentView(R.layout.forget_password)
        //setContentView(R.layout.forget_password_cont)
        //setContentView(R.layout.reset_password)
        setContentView(R.layout.homepage)

        //toolbar=findViewById<Toolbar>(R.id.myToolBar)

        //setSupportActionBar(findViewById(R.id.myToolBar))
    }
}
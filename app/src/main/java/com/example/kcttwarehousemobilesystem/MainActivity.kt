package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {

    //private lateinit var toolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.register)
        setContentView(R.layout.login)
        //setContentView(R.layout.forget_password)
        //setContentView(R.layout.forget_password_cont)
        //setContentView(R.layout.reset_password)
        //setContentView(R.layout.homepage)

        //toolbar=findViewById<Toolbar>(R.id.myToolBar)

        //setSupportActionBar(findViewById(R.id.myToolBar))

        btnLogin.setOnClickListener{
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }

    }
}
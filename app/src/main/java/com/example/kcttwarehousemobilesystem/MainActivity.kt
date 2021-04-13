package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*

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

        //login btn to homepage
        /*btnLogin.setOnClickListener{
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }*/
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true;
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when(itemView){
            R.id.acc_manage_reset -> Toast.
        }
    }*/
}
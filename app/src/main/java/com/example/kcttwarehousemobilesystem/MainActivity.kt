package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //setContentView(R.layout.activity_login)
        //setContentView(R.layout.activity_forget_password)
        //setContentView(R.layout.forget_password_cont)
        //setContentView(R.layout.reset_password)
        //setContentView(R.layout.activity_main)
        //setContentView(R.layout.fragment_login)

        //Login page
        //Login button
/*        btnLogin.setOnClickListener {
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }

        //Sign Up text button
        textBtnSignUp.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        //Forget pass text button
        textBtnForgotPass.setOnClickListener {
            val intent = Intent(this, Forget_password::class.java)
            startActivity(intent)
        }*/

        //Register page
        //Sign Up button
        /*btnSignUp.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        //Sign In text button
        textBtnSignIn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }*/

        //Forget Password page
        //Send button
/*        btnSendForget.setOnClickListener {
            val intent = Intent(this, Forget_password_cont::class.java)
            startActivity(intent)
        }*/


        //Homepage
        /*toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mItem1 -> Toast.makeText(applicationContext,
                    "Clicked Register Material", Toast.LENGTH_SHORT).show()
                R.id.mItem2 -> Toast.makeText(applicationContext,
                    "Clicked Materials List", Toast.LENGTH_SHORT).show()
                R.id.mItem3 -> Toast.makeText(applicationContext,
                    "Clicked Receive Materials", Toast.LENGTH_SHORT).show()
                R.id.mItem4 -> Toast.makeText(applicationContext,
                    "Clicked Retrieve from Rack", Toast.LENGTH_SHORT).show()
                R.id.mItem5 -> Toast.makeText(applicationContext,
                    "Clicked Warehouse Map", Toast.LENGTH_SHORT).show()
                R.id.mItem6 -> Toast.makeText(applicationContext,
                        "Clicked Warehouse Map", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        var itemview = item.itemId
        when(itemview){

            R.id.acc_manage_reset -> {
                val intent = Intent(this, Reset::class.java)
                startActivity(intent)
            }

            R.id.acc_manage_logout -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }

        //return false
        return super.onOptionsItemSelected(item)
    }

    //main menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true*/
    }
}
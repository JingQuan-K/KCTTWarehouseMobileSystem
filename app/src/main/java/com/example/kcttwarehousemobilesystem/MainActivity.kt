package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.system.Os.close
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*
import java.nio.channels.AsynchronousFileChannel.open

class MainActivity : AppCompatActivity() {
    
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.register)
        //setContentView(R.layout.login)
        setContentView(R.layout.forget_password)
        //setContentView(R.layout.forget_password_cont)
        //setContentView(R.layout.reset_password)
        //setContentView(R.layout.activity_main)
        //setContentView(R.layout.fragment_login)

/*        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mItem1 -> Toast.makeText(applicationContext,
                        "Clicked Item 1", Toast.LENGTH_SHORT).show()
                R.id.mItem2 -> Toast.makeText(applicationContext,
                        "Clicked Item 2", Toast.LENGTH_SHORT).show()
                R.id.mItem3 -> Toast.makeText(applicationContext,
                        "Clicked Item 3", Toast.LENGTH_SHORT).show()
                R.id.mItem4 -> Toast.makeText(applicationContext,
                        "Clicked Item 4", Toast.LENGTH_SHORT).show()
                R.id.mItem5 -> Toast.makeText(applicationContext,
                        "Clicked Item 5", Toast.LENGTH_SHORT).show()
                R.id.mItem6 -> Toast.makeText(applicationContext,
                        "Clicked Item 6", Toast.LENGTH_SHORT).show()
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

            R.id.acc_manage_reset -> Toast.makeText(applicationContext, "Reset Clicked", Toast.LENGTH_SHORT).show()
            R.id.acc_manage_logout -> Toast.makeText(applicationContext, "Log out Clicked", Toast.LENGTH_SHORT).show()
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
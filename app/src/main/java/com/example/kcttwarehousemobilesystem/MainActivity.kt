package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kcttwarehousemobilesystem.entity.Rack
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        val dao = UserDatabase.getDatabase(this).userDao()

        val racks = listOf(
            Rack("A_01a_01", 1),
            Rack("A_01a_02", 2)
        )

        lifecycleScope.launch{
            racks.forEach{dao.addRack(it)}
        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        toggle.drawerArrowDrawable.color = resources.getColor(R.color.Cyan)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mItem1 -> {
                    val intent = Intent(this, MainMaterialAct::class.java)
                    startActivity(intent)
                }
                R.id.mItem2 -> {
                    val intent = Intent(this, MainMaterialList::class.java)
                    startActivity(intent)
                }
                R.id.mItem3 -> {
                    val intent = Intent(this, ReceiveMaterialsScanner::class.java)
                    startActivity(intent)
                }
                R.id.mItem4 -> Toast.makeText(applicationContext,
                        "Clicked Retrieve from Rack", Toast.LENGTH_SHORT).show()
                R.id.mItem5 -> {
                    val intent = Intent(this, WarehouseMap::class.java)
                    startActivity(intent)
                }
                R.id.mItem6 -> Toast.makeText(applicationContext,
                        "Clicked Report", Toast.LENGTH_SHORT).show()
            }
            true
        }

        register_material_btn.setOnClickListener {
            val intent = Intent(this, MainMaterialAct::class.java)
            startActivity(intent)
        }

        materials_list_btn.setOnClickListener {
            val intent = Intent(this, MainMaterialList::class.java)
            startActivity(intent)
        }

        receive_materials_btn.setOnClickListener {
            val intent = Intent(this, ReceiveMaterialsScanner::class.java)
            startActivity(intent)
        }

        retrieve_from_rack_btn.setOnClickListener{

        }

        warehouse_map_btn.setOnClickListener {
            val intent = Intent(this, WarehouseMap::class.java)
            startActivity(intent)
        }

        report_btn.setOnClickListener {

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        var itemview = item.itemId
        when(itemview){

            R.id.acc_manage_reset -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            R.id.acc_manage_logout -> {
                val user = auth.currentUser
                if (user != null) {
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
        }

        //return false
        return super.onOptionsItemSelected(item)
    }

    //main menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser!=null){
            auth.signOut()
            updateUI(null)
            Toast.makeText(this, "You Signed Out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Login::class.java))
            finish()
        }
        else{
            auth.signOut()
            Toast.makeText(this, "Log Out Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
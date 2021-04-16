package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kcttwarehousemobilesystem.entity.Material
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import com.example.kcttwarehousemobilesystem.entity.Rack
import com.example.kcttwarehousemobilesystem.entity.UserDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //database stuff
        val dao = UserDatabase.getDatabase(this).userDao()

        val material_type = listOf(
                MaterialType(1, "Wood")
        )

        val materials = listOf(
                Material(1, "Alpha Table", "photo path", 20, 45.00,100.00, 10, 1),
                Material(2, "Beta Table", "photo path", 20, 100.00,9900.00, 5, 1),
                Material(3, "Charlie Table", "photo path", 60, 100.00,9900.00, 5, 1),
                Material(4, "Delta Table", "photo path", 60, 100.00,9900.00, 5, 1)
        )

        val racks = listOf(
                Rack("A_01a_01", 1,  20),
                Rack("A_01b_01", 1,  0),
                Rack("A_01a_02", 2,20),
                Rack("A_02a_01", 3, 60),
                Rack("B_02a_01", 4, 30),
                Rack("B_02a_02", 4, 30),
                Rack("B_05_05" , 0, 0)

        )

        lifecycleScope.launch{
            material_type.forEach{dao.addMaterialType(it)}
            materials.forEach{dao.addMaterial(it)}
            racks.forEach{dao.addRack(it)}
        }


        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

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
            val intent = Intent(this, RetrieveFromRackScanner::class.java)
            startActivity(intent)
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
        }else if(intent.hasExtra(MaterialDetailsRetrieve.MATERIAL_NAME) && intent.hasExtra(MaterialDetailsRetrieve.QUANTITY)){
            val rackQuantity = intent?.extras?.getInt(MaterialDetailsRetrieve.QUANTITY).toString().toInt()
            val materialName = intent?.extras?.getString(MaterialDetailsRetrieve.MATERIAL_NAME).toString()
            val toastMsg = "Successfully retrieved $rackQuantity $materialName"
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

            R.id.acc_manage_reset -> Toast.makeText(applicationContext, "Reset Clicked", Toast.LENGTH_SHORT).show()
            R.id.acc_manage_logout -> Toast.makeText(applicationContext, "Log out Clicked", Toast.LENGTH_SHORT).show()
        }

        //return false
        return super.onOptionsItemSelected(item)
    }

    //main menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }
}
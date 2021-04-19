package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kcttwarehousemobilesystem.database.KCTTDatabase
import com.example.kcttwarehousemobilesystem.entity.Rack
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateDatabase()

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
                R.id.mItem4 -> {
                    val intent = Intent(this, RetrieveFromRackScanner::class.java)
                    startActivity(intent)
                }
                R.id.mItem5 -> {
                    val intent = Intent(this, WarehouseMap::class.java)
                    startActivity(intent)
                }
                R.id.mItem6 -> {
                    val intent = Intent(this, Report::class.java)
                    startActivity(intent)
                }
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
            val intent = Intent(this,Report::class.java)
            startActivity(intent)
        }

        //Display Toast if successfully placed material to rack or retrieved material from rack
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

    private fun populateDatabase(){
        val dao = KCTTDatabase.getDatabase(this).userDao()

        val racks = listOf(
                Rack("A_01a_01", 0, 0),
                Rack("A_01a_02", 0, 0),
                Rack("A_01a_03", 0, 0),
                Rack("A_01a_04", 0, 0),
                Rack("A_01a_05", 0, 0),
                Rack("A_01a_06", 0, 0),
                Rack("A_01a_07", 0, 0),
                Rack("A_01a_08", 0, 0),
                Rack("A_01a_09", 0, 0),
                Rack("A_01a_10", 0, 0),
                Rack("A_01a_11", 0, 0),
                Rack("A_01a_12", 0, 0),

                Rack("A_01b_01", 0, 0),
                Rack("A_01b_02", 0, 0),
                Rack("A_01b_03", 0, 0),
                Rack("A_01b_04", 0, 0),
                Rack("A_01b_05", 0, 0),
                Rack("A_01b_06", 0, 0),
                Rack("A_01b_07", 0, 0),
                Rack("A_01b_08", 0, 0),
                Rack("A_01b_09", 0, 0),
                Rack("A_01b_10", 0, 0),
                Rack("A_01b_11", 0, 0),
                Rack("A_01b_12", 0, 0),

                Rack("A_02a_01", 0, 0),
                Rack("A_02a_02", 0, 0),
                Rack("A_02a_03", 0, 0),
                Rack("A_02a_04", 0, 0),
                Rack("A_02a_05", 0, 0),
                Rack("A_02a_06", 0, 0),
                Rack("A_02a_07", 0, 0),
                Rack("A_02a_08", 0, 0),
                Rack("A_02a_09", 0, 0),
                Rack("A_02a_10", 0, 0),
                Rack("A_02a_11", 0, 0),
                Rack("A_02a_12", 0, 0),

                Rack("A_02b_01", 0, 0),
                Rack("A_02b_02", 0, 0),
                Rack("A_02b_03", 0, 0),
                Rack("A_02b_04", 0, 0),
                Rack("A_02b_05", 0, 0),
                Rack("A_02b_06", 0, 0),
                Rack("A_02b_07", 0, 0),
                Rack("A_02b_08", 0, 0),
                Rack("A_02b_09", 0, 0),
                Rack("A_02b_10", 0, 0),
                Rack("A_02b_11", 0, 0),
                Rack("A_02b_12", 0, 0),

                Rack("A_03a_01", 0, 0),
                Rack("A_03a_02", 0, 0),
                Rack("A_03a_03", 0, 0),
                Rack("A_03a_04", 0, 0),
                Rack("A_03a_05", 0, 0),
                Rack("A_03a_06", 0, 0),
                Rack("A_03a_07", 0, 0),
                Rack("A_03a_08", 0, 0),
                Rack("A_03a_09", 0, 0),
                Rack("A_03a_10", 0, 0),
                Rack("A_03a_11", 0, 0),
                Rack("A_03a_12", 0, 0),

                Rack("A_03b_01", 0, 0),
                Rack("A_03b_02", 0, 0),
                Rack("A_03b_03", 0, 0),
                Rack("A_03b_04", 0, 0),
                Rack("A_03b_05", 0, 0),
                Rack("A_03b_06", 0, 0),
                Rack("A_03b_07", 0, 0),
                Rack("A_03b_08", 0, 0),
                Rack("A_03b_09", 0, 0),
                Rack("A_03b_10", 0, 0),
                Rack("A_03b_11", 0, 0),
                Rack("A_03b_12", 0, 0),

                Rack("B_01a_01", 0, 0),
                Rack("B_01a_02", 0, 0),
                Rack("B_01a_03", 0, 0),
                Rack("B_01a_04", 0, 0),
                Rack("B_01a_05", 0, 0),
                Rack("B_01a_06", 0, 0),
                Rack("B_01a_07", 0, 0),
                Rack("B_01a_08", 0, 0),
                Rack("B_01a_09", 0, 0),
                Rack("B_01a_10", 0, 0),
                Rack("B_01a_11", 0, 0),
                Rack("B_01a_12", 0, 0),

                Rack("B_01b_01", 0, 0),
                Rack("B_01b_02", 0, 0),
                Rack("B_01b_03", 0, 0),
                Rack("B_01b_04", 0, 0),
                Rack("B_01b_05", 0, 0),
                Rack("B_01b_06", 0, 0),
                Rack("B_01b_07", 0, 0),
                Rack("B_01b_08", 0, 0),
                Rack("B_01b_09", 0, 0),
                Rack("B_01b_10", 0, 0),
                Rack("B_01b_11", 0, 0),
                Rack("B_01b_12", 0, 0),

                Rack("B_02a_01", 0, 0),
                Rack("B_02a_02", 0, 0),
                Rack("B_02a_03", 0, 0),
                Rack("B_02a_04", 0, 0),
                Rack("B_02a_05", 0, 0),
                Rack("B_02a_06", 0, 0),
                Rack("B_02a_07", 0, 0),
                Rack("B_02a_08", 0, 0),
                Rack("B_02a_09", 0, 0),
                Rack("B_02a_10", 0, 0),
                Rack("B_02a_11", 0, 0),
                Rack("B_02a_12", 0, 0),

                Rack("B_02b_01", 0, 0),
                Rack("B_02b_02", 0, 0),
                Rack("B_02b_03", 0, 0),
                Rack("B_02b_04", 0, 0),
                Rack("B_02b_05", 0, 0),
                Rack("B_02b_06", 0, 0),
                Rack("B_02b_07", 0, 0),
                Rack("B_02b_08", 0, 0),
                Rack("B_02b_09", 0, 0),
                Rack("B_02b_10", 0, 0),
                Rack("B_02b_11", 0, 0),
                Rack("B_02b_12", 0, 0),

                Rack("B_03a_01", 0, 0),
                Rack("B_03a_02", 0, 0),
                Rack("B_03a_03", 0, 0),
                Rack("B_03a_04", 0, 0),
                Rack("B_03a_05", 0, 0),
                Rack("B_03a_06", 0, 0),
                Rack("B_03a_07", 0, 0),
                Rack("B_03a_08", 0, 0),
                Rack("B_03a_09", 0, 0),
                Rack("B_03a_10", 0, 0),
                Rack("B_03a_11", 0, 0),
                Rack("B_03a_12", 0, 0),

                Rack("B_03b_01", 0, 0),
                Rack("B_03b_02", 0, 0),
                Rack("B_03b_03", 0, 0),
                Rack("B_03b_04", 0, 0),
                Rack("B_03b_05", 0, 0),
                Rack("B_03b_06", 0, 0),
                Rack("B_03b_07", 0, 0),
                Rack("B_03b_08", 0, 0),
                Rack("B_03b_09", 0, 0),
                Rack("B_03b_10", 0, 0),
                Rack("B_03b_11", 0, 0),
                Rack("B_03b_12", 0, 0),

                Rack("B_04a_01", 0, 0),
                Rack("B_04a_02", 0, 0),
                Rack("B_04a_03", 0, 0),
                Rack("B_04a_04", 0, 0),
                Rack("B_04a_05", 0, 0),
                Rack("B_04a_06", 0, 0),
                Rack("B_04a_07", 0, 0),
                Rack("B_04a_08", 0, 0),
                Rack("B_04a_09", 0, 0),
                Rack("B_04a_10", 0, 0),
                Rack("B_04a_11", 0, 0),
                Rack("B_04a_12", 0, 0),

                Rack("B_04b_01", 0, 0),
                Rack("B_04b_02", 0, 0),
                Rack("B_04b_03", 0, 0),
                Rack("B_04b_04", 0, 0),
                Rack("B_04b_05", 0, 0),
                Rack("B_04b_06", 0, 0),
                Rack("B_04b_07", 0, 0),
                Rack("B_04b_08", 0, 0),
                Rack("B_04b_09", 0, 0),
                Rack("B_04b_10", 0, 0),
                Rack("B_04b_11", 0, 0),
                Rack("B_04b_12", 0, 0),

                Rack("B_05a_01", 0, 0),
                Rack("B_05a_02", 0, 0),
                Rack("B_05a_03", 0, 0),
                Rack("B_05a_04", 0, 0),
                Rack("B_05a_05", 0, 0),
                Rack("B_05a_06", 0, 0),
                Rack("B_05a_07", 0, 0),
                Rack("B_05a_08", 0, 0),
                Rack("B_05a_09", 0, 0),
                Rack("B_05a_10", 0, 0),
                Rack("B_05a_11", 0, 0),
                Rack("B_05a_12", 0, 0),

                Rack("B_05b_01", 0, 0),
                Rack("B_05b_02", 0, 0),
                Rack("B_05b_03", 0, 0),
                Rack("B_05b_04", 0, 0),
                Rack("B_05b_05", 0, 0),
                Rack("B_05b_06", 0, 0),
                Rack("B_05b_07", 0, 0),
                Rack("B_05b_08", 0, 0),
                Rack("B_05b_09", 0, 0),
                Rack("B_05b_10", 0, 0),
                Rack("B_05b_11", 0, 0),
                Rack("B_05b_12", 0, 0),
        )

        lifecycleScope.launch{
            racks.forEach{dao.addRack(it)}
        }
    }
}
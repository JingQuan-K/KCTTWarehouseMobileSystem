package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kcttwarehousemobilesystem.database.KCTTDatabase
import com.example.kcttwarehousemobilesystem.ViewModel.WarehouseMapViewModel
import com.example.kcttwarehousemobilesystem.ViewModel.WarehouseMapViewModelFactory
import kotlinx.android.synthetic.main.activity_material_details.*
import kotlinx.android.synthetic.main.activity_warehouse_map.*
import kotlinx.coroutines.launch


class WarehouseMap : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warehouse_map)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search material"
        title = "Warehouse Map"

        //viewModel
        val dataSource = KCTTDatabase.getDatabase(application).userDao()
        val viewModelFactory = WarehouseMapViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(WarehouseMapViewModel::class.java)
        viewModel.searchedRackList.observe(this, {
            viewModel.getAllRacks()
            viewModel.allRacks.forEach { r ->
                if(r[0] == 'A'){
                    val rack = findViewById<ImageView>(resources.getIdentifier(r,"id", this@WarehouseMap.packageName))
                    rack.setImageResource(R.drawable.square_zonea)
                }else{
                    val rack = findViewById<ImageView>(resources.getIdentifier(r,"id", this@WarehouseMap.packageName))
                    rack.setImageResource(R.drawable.square_zoneb)
                }
            }

            if(it.isNotEmpty()){
                it.forEach{ r ->
                    val rack = findViewById<ImageView>(resources.getIdentifier(r,"id", this@WarehouseMap.packageName))
                    rack.setImageResource(R.drawable.square_found)
                }
            }
        })

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                //search
                val dao = KCTTDatabase.getDatabase(this@WarehouseMap).userDao()
                if(newText.toString() == ""){
                    viewModel.searchedRackList.value = listOf()
                }else{
                    lifecycleScope.launch {
                        viewModel.searchedRackList.value = dao.getRacksOfMaterial(newText.toString())
                    }
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
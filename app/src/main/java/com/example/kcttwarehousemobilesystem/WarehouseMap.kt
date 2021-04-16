package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kcttwarehousemobilesystem.entity.UserDatabase
import com.example.kcttwarehousemobilesystem.entity.WarehouseMapViewModel
import com.example.kcttwarehousemobilesystem.entity.WarehouseMapViewModelFactory
import kotlinx.android.synthetic.main.activity_warehouse_map.*


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

        //viewModel
        val dataSource = UserDatabase.getDatabase(application).userDao()
        val viewModelFactory = WarehouseMapViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(WarehouseMapViewModel::class.java)


        viewModel.rackList.forEach{
            val rack = findViewById<ImageView>(resources.getIdentifier(it,"id", this@WarehouseMap.packageName))
            rack.setImageResource(R.drawable.square_found)
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                //clear
                viewModel.getAllRacks()
                viewModel.allRacks.forEach {
                    if(it[0] == 'A'){
                        val rack = findViewById<ImageView>(resources.getIdentifier(it,"id", this@WarehouseMap.packageName))
                        rack.setImageResource(R.drawable.square_zonea)
                    }else{
                        val rack = findViewById<ImageView>(resources.getIdentifier(it,"id", this@WarehouseMap.packageName))
                        rack.setImageResource(R.drawable.square_zoneb)
                    }
                }

                //search
                viewModel.search(newText.toString())
                if(viewModel.rackList.isNullOrEmpty()){
                    //Toast.makeText(this@WarehouseMap, "Empty", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.rackList.forEach{
                        val rack = findViewById<ImageView>(resources.getIdentifier(it,"id", this@WarehouseMap.packageName))
                        rack.setImageResource(R.drawable.square_found)
                    }
                }

                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}
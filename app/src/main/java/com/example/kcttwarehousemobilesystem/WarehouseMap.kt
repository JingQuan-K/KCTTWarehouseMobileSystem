package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
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
        val viewModelFactory = WarehouseMapViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(WarehouseMapViewModel::class.java)






        //var rackList = listOf<String>("A_01a_01", "B_02a_02")
        /*       rackList.forEach{
                 val rack = findViewById<ImageView>(resources.getIdentifier(it,"id", this@WarehouseMap.packageName))
                 rack.setImageResource(R.drawable.square_found)
             }*/


/*        viewModel.rackList.forEach{
            val rack = findViewById<ImageView>(resources.getIdentifier(it,"id", this@WarehouseMap.packageName))
            rack.setImageResource(R.drawable.square_found)
        }*/

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //viewModel.search(newText.toString())
                //Toast.makeText(this@WarehouseMap, "tst", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@WarehouseMap, "test", Toast.LENGTH_SHORT).show()
                //var one = "1"
                //Get from Database
                val dao = UserDatabase.getDatabase(this@WarehouseMap).userDao()
                //var temp = dao.getMaterialQuantity(1)
                //viewModel.searchById()
                //Toast.makeText(this@WarehouseMap, viewModel.rack, Toast.LENGTH_SHORT).show()


                //viewModel.searchById()
                //val rack = findViewById<ImageView>(resources.getIdentifier(temp,"id", this@WarehouseMap.packageName))
                //rack.setImageResource(R.drawable.square_found)

                /*viewModel.rackList.forEach{
                    val rack = findViewById<ImageView>(resources.getIdentifier(it,"id", this@WarehouseMap.packageName))
                    rack.setImageResource(R.drawable.square_found)
                }*/

                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}
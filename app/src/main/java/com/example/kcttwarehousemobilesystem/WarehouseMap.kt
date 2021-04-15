package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
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
        val viewModelFactory = WarehouseMapViewModelFactory(dataSource, application)
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




                //Get from Database
                //val dao = UserDatabase.getDatabase(this@WarehouseMap).userDao()
                //var temp = dao.getMaterialQuantity(1)


                //Toast.makeText(this@WarehouseMap, viewModel.rack, Toast.LENGTH_SHORT).show()


                loading_bay_text.text = "testing"
                //viewModel.searchById()

                viewModel.search(newText.toString())


                //val temp = viewModel.rack
                loading_bay_text.text = newText.toString()

                //val rack = findViewById<ImageView>(resources.getIdentifier(viewModel.rack,"id", this@WarehouseMap.packageName))
                //rack.setImageResource(R.drawable.square_found)

                if(viewModel.rackList.isNullOrEmpty()){
                    Toast.makeText(this@WarehouseMap, "Empty", Toast.LENGTH_SHORT).show()
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
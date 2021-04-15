package com.example.kcttwarehousemobilesystem.entity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/*class WarehouseMapViewModel(
        private val database: UserDao): ViewModel(){

    //private val repo: WarehouseMapRepository
//val dao = UserDatabase.getDatabase(this@ReceiveMaterialsScanner).userDao()



    var rackList: List<String> = listOf("A_01a_01", "A_01a_02")
    var rack: String = "";

    fun search(materialName:String){
        viewModelScope.launch {
            rackList = listOf("A_01b_01", "A_01b_02")
            //rackList = database.getRacksOfMaterial(materialName)
        }
    }

     fun searchById(){

         //val temp = database.getMaterialQuantity(1)
         viewModelScope.launch {
             //val temp = database.getMaterialQuantity(1)
             //rack = temp.toString()
             rack = "asda"

        }



    }

}*/

/*class WarehouseMapViewModel: ViewModel(){

    var rackList: List<String> = listOf("A_01a_01", "A_01a_02")

    fun search(materialName:String) {
        rackList = listOf("A_01a_01", "A_01a_02", "A_01a_05")
    }

}*/

class WarehouseMapViewModel(private val database: UserDao,application: Application): AndroidViewModel(application) {

    private val repository: UserRepository

    var rackList: List<String> = listOf()
    var rack: String = "lol";

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)

    }

    fun search(materialId:String){
        viewModelScope.launch {

            //var temp = repository.getRacksOfMaterial(materialName)
            //rackList = listOf(temp.toString())
            var temp = materialId.toInt()
            rackList = database.getRacksOfMaterial(temp)
        }
    }

    fun searchById() {

        viewModelScope.launch {
            //val id = materialId.toInt()
            var temp = database.getRackOfMaterial(1)
            //val temp = database.getMaterialQuantity(1)
            rack = temp


        }

    }
}

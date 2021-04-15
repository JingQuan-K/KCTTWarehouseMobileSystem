package com.example.kcttwarehousemobilesystem.entity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WarehouseMapViewModel(
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

}

/*class WarehouseMapViewModel: ViewModel(){

    var rackList: List<String> = listOf("A_01a_01", "A_01a_02")

    fun search(materialName:String) {
        rackList = listOf("A_01a_01", "A_01a_02", "A_01a_05")
    }

}*/

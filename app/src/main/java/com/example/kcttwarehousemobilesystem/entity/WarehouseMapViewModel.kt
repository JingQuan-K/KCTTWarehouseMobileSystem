package com.example.kcttwarehousemobilesystem.entity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WarehouseMapViewModel(private val database: UserDao,application: Application): AndroidViewModel(application) {

    var allRacks: List<String> = listOf()
    var rackList: List<String> = listOf()
    var rack: String = "";

    fun getAllRacks(){
        viewModelScope.launch{
            allRacks = database.getAllRacks()
        }
    }

    fun search(materialName: String){
        viewModelScope.launch {
            rackList = database.getRacksOfMaterial(materialName)
        }
    }


}
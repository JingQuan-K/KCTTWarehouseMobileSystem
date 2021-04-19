package com.example.kcttwarehousemobilesystem.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kcttwarehousemobilesystem.database.KCTTDao
import kotlinx.coroutines.launch

class WarehouseMapViewModel(private val database: KCTTDao, application: Application): AndroidViewModel(application) {

    var allRacks: List<String> = listOf()

    val searchedRackList: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    fun getAllRacks(){
        viewModelScope.launch{
            allRacks = database.getAllRacks()
        }
    }

}
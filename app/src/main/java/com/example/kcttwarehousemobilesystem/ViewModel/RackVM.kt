package com.example.kcttwarehousemobilesystem.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kcttwarehousemobilesystem.entity.Rack
import com.example.kcttwarehousemobilesystem.database.KCTTDatabase
import com.example.kcttwarehousemobilesystem.database.KCTTRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RackVM(application: Application): AndroidViewModel(application) {

    val getAllRack: LiveData<List<Rack>>
    private val repository: KCTTRepository

    init {
        val userDao = KCTTDatabase.getDatabase(application).userDao()
        repository = KCTTRepository(userDao)
        getAllRack = repository.getAllRack
    }

    fun addRack(mt: Rack){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRack(mt)
        }
    }
}
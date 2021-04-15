package com.example.kcttwarehousemobilesystem.materialType

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kcttwarehousemobilesystem.entity.Rack
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import com.example.kcttwarehousemobilesystem.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RackVM(application: Application): AndroidViewModel(application) {

    val getAllRack: LiveData<List<Rack>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllRack = repository.getAllRack
    }

    fun addRack(mt: Rack){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRack(mt)
        }
    }
}
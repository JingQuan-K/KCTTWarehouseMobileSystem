package com.example.kcttwarehousemobilesystem.materialType

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kcttwarehousemobilesystem.entity.Material
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import com.example.kcttwarehousemobilesystem.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaterialVM(application: Application): AndroidViewModel(application) {

    val getAllMaterial: LiveData<List<Material>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllMaterial = repository.getAllMaterial
    }

    fun addMaterial(mt: Material){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMaterial(mt)
        }
    }
}
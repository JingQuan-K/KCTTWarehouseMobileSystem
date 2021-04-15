package com.example.kcttwarehousemobilesystem.materialType

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import com.example.kcttwarehousemobilesystem.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaterialTypeVM(application: Application): AndroidViewModel(application) {

    val getAllMaterialType: LiveData<List<MaterialType>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllMaterialType = repository.getAllMaterialType
    }

    fun addMaterialType(mt: MaterialType){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMaterialType(mt)
        }
    }
}
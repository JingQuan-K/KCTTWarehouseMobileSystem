package com.example.kcttwarehousemobilesystem.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import com.example.kcttwarehousemobilesystem.database.KCTTDatabase
import com.example.kcttwarehousemobilesystem.database.KCTTRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaterialTypeVM(application: Application): AndroidViewModel(application) {

    val getAllMaterialType: LiveData<List<MaterialType>>
    private val repository: KCTTRepository

    init {
        val userDao = KCTTDatabase.getDatabase(application).userDao()
        repository = KCTTRepository(userDao)
        getAllMaterialType = repository.getAllMaterialType
    }

    fun addMaterialType(mt: MaterialType){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMaterialType(mt)
        }
    }
}
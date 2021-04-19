package com.example.kcttwarehousemobilesystem.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kcttwarehousemobilesystem.entity.Material
import com.example.kcttwarehousemobilesystem.database.KCTTDatabase
import com.example.kcttwarehousemobilesystem.database.KCTTRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaterialVM(application: Application): AndroidViewModel(application) {

    val getAllMaterial: LiveData<List<Material>>
    private val repository: KCTTRepository
    val materialVal: MutableLiveData<List<Material>> by lazy {
        MutableLiveData<List<Material>>()
    }
    val materialValLow: MutableLiveData<List<Material>> by lazy {
        MutableLiveData<List<Material>>()
    }

    init {
        val userDao = KCTTDatabase.getDatabase(application).userDao()
        repository = KCTTRepository(userDao)
        getAllMaterial = repository.getAllMaterial
    }

    fun addMaterial(mt: Material){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMaterial(mt)
        }
    }
}
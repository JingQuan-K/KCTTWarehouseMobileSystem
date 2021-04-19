package com.example.kcttwarehousemobilesystem.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kcttwarehousemobilesystem.database.KCTTDao

class WarehouseMapViewModelFactory (private val dataSource: KCTTDao, private val application: Application)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WarehouseMapViewModel::class.java)) {
            return WarehouseMapViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
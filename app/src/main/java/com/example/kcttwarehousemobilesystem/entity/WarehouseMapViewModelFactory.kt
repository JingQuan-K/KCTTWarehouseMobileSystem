package com.example.kcttwarehousemobilesystem.entity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WarehouseMapViewModelFactory (private val dataSource: UserDao)
    : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WarehouseMapViewModel::class.java)) {
                return WarehouseMapViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}

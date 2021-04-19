package com.example.kcttwarehousemobilesystem.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kcttwarehousemobilesystem.database.KCTTDatabase
import com.example.kcttwarehousemobilesystem.database.KCTTRepository
import com.example.kcttwarehousemobilesystem.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionsVM(application: Application): AndroidViewModel(application) {

    val getAllTransactions: LiveData<List<Transactions>>
    private val repository: KCTTRepository

    init {
        val userDao = KCTTDatabase.getDatabase(application).userDao()
        repository = KCTTRepository(userDao)
        getAllTransactions = repository.getAllTransactions

    }

    fun addTransactions(mt: Transactions){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransactions(mt)
        }
    }
}
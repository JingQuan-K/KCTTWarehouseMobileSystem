package com.example.kcttwarehousemobilesystem.materialType

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import com.example.kcttwarehousemobilesystem.database.UserRepository
import com.example.kcttwarehousemobilesystem.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionsVM(application: Application): AndroidViewModel(application) {

    val getAllTransactions: LiveData<List<Transactions>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllTransactions = repository.getAllTransactions
    }

    fun addTransactions(mt: Transactions){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransactions(mt)
        }
    }
}
package com.example.kcttwarehousemobilesystem.database

import androidx.lifecycle.LiveData
import com.example.kcttwarehousemobilesystem.entity.*

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun addMaterialType(mt: MaterialType){
        userDao.addMaterialType(mt)
    }

    suspend fun addMaterial(mt: Material){
        userDao.addMaterial(mt)
    }

    suspend fun addRack(mt: Rack){
        userDao.addRack(mt)
    }

    suspend fun addTransactions(mt: Transactions){
        userDao.addTransactions(mt)
    }

    val getAllMaterialType: LiveData<List<MaterialType>> = userDao.getAllMaterialType()
    val getAllMaterial: LiveData<List<Material>> = userDao.getAllMaterial()
    val getAllRack: LiveData<List<Rack>> = userDao.getAllRack()
    val getAllTransactions: LiveData<List<Transactions>> = userDao.getAllTransactions()
}
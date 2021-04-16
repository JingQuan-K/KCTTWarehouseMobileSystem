package com.example.kcttwarehousemobilesystem.entity

import androidx.lifecycle.LiveData

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


}
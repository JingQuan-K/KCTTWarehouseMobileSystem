package com.example.kcttwarehousemobilesystem.entity

import androidx.lifecycle.LiveData

class WarehouseMapRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun getRackOfMaterial(materialId: Int){
        userDao.getRackOfMaterial(materialId)
    }




}
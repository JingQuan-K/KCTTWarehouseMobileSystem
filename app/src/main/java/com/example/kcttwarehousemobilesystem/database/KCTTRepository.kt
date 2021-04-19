package com.example.kcttwarehousemobilesystem.database

import androidx.lifecycle.LiveData
import com.example.kcttwarehousemobilesystem.entity.Material
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import com.example.kcttwarehousemobilesystem.entity.Rack
import com.example.kcttwarehousemobilesystem.entity.Transactions

class KCTTRepository(private val KCTTDao: KCTTDao) {

    val getAllMaterialType: LiveData<List<MaterialType>> = KCTTDao.getAllMaterialType()
    val getAllMaterial: LiveData<List<Material>> = KCTTDao.getAllMaterial()
    val getAllRack: LiveData<List<Rack>> = KCTTDao.getAllRack()
    val getAllTransactions: LiveData<List<Transactions>> = KCTTDao.getAllTransactions()

    suspend fun addMaterialType(mt: MaterialType){
        KCTTDao.addMaterialType(mt)
    }

    suspend fun addMaterial(mt: Material){
        KCTTDao.addMaterial(mt)
    }

    suspend fun addRack(mt: Rack){
        KCTTDao.addRack(mt)
    }

    suspend fun addTransactions(mt: Transactions){
        KCTTDao.addTransactions(mt)
    }


}
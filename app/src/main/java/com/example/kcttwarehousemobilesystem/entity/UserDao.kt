package com.example.kcttwarehousemobilesystem.entity

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransaction(transactions: Transactions)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMaterial(material: Material)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMaterialType(mt: MaterialType)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRack(rack: Rack)

    @Query("SELECT * FROM user_table")
    fun readAllData(): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM materialType_table WHERE MaterialTypeId = :MaterialTypeId ")
    suspend fun getMaterialAndType(MaterialTypeId:String): List<MaterialAndType>

    @Transaction
    @Query("SELECT * FROM material_table WHERE MaterialId = :MaterialId")
    suspend fun getMaterialAndTrans(MaterialId:String): List<MaterialAndTrans>

    @Transaction
    @Query("SELECT * FROM user_table WHERE userId = :userId")
    suspend fun getUserAndTrans(userId:String): List<UserAndTrans>

    @Query("SELECT * FROM materialType_table ORDER BY MaterialTypeId DESC")
    fun getAllMaterialType(): LiveData<List<MaterialType>>
}


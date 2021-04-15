package com.example.kcttwarehousemobilesystem.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kcttwarehousemobilesystem.entity.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransactions(transactions: Transactions)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMaterial(material: Material)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMaterialType(mt: MaterialType)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRack(rack: Rack)


    @Query("SELECT * FROM user_table")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM materialType_table ORDER BY MaterialTypeId DESC")
    fun getAllMaterialType(): LiveData<List<MaterialType>>

    @Query("SELECT * FROM material_table ORDER BY MaterialId DESC")
    fun getAllMaterial(): LiveData<List<Material>>

    @Query("SELECT * FROM rack_table ORDER BY RackId DESC")
    fun getAllRack(): LiveData<List<Rack>>

    @Query("SELECT * FROM transaction_table ORDER BY TransactionId DESC")
    fun getAllTransactions(): LiveData<List<Transactions>>

    @Transaction
    @Query("SELECT * FROM materialType_table WHERE MaterialTypeId = :MaterialTypeId ")
    suspend fun getMaterialAndType(MaterialTypeId:String): List<MaterialAndType>

    @Transaction
    @Query("SELECT * FROM material_table WHERE MaterialId = :MaterialId")
    suspend fun getMaterialAndTrans(MaterialId:String): List<MaterialAndTrans>

    @Transaction
    @Query("SELECT * FROM user_table WHERE userId = :userId")
    suspend fun getUserAndTrans(userId:String): List<UserAndTrans>


}


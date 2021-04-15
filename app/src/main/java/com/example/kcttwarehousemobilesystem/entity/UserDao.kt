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
    suspend fun addMaterialType(materialType: MaterialType)





    @Query("SELECT * FROM user_table")
    fun readAllData(): LiveData<List<User>>

    //MATERIAL
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMaterial(material: Material)

    @Query("SELECT * FROM material_table WHERE MaterialId = :MaterialId")
    fun getMaterial(MaterialId: Int) : Material

    @Query("SELECT Quantity FROM material_table WHERE MaterialId = :MaterialId")
    fun getMaterialQuantity(MaterialId: Int) : Int

    @Query("UPDATE material_table SET Quantity = :Quantity WHERE MaterialId = :MaterialId")
    fun setMaterialQuantity(Quantity: Int, MaterialId: Int)

    //RACK
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRack(rack: Rack)

    @Query("SELECT RackId FROM rack_table, material_table WHERE MaterialName = :MaterialName AND rack_table.MaterialId = material_table.MaterialId")
    fun getRacksOfMaterial(MaterialName:String): List<String>

    @Query("SELECT RackId FROM rack_table WHERE MaterialId = :MaterialId")
    fun getRackOfMaterial(MaterialId: Int) : String


    //getTypeWithMaterials
    @Transaction
    @Query("SELECT * FROM materialType_table WHERE MaterialTypeId = :MaterialTypeId ")
    suspend fun getMaterialAndType(MaterialTypeId:String): List<MaterialAndType>

    //getMaterialWithTransactions
    @Transaction
    @Query("SELECT * FROM material_table WHERE MaterialId = :MaterialId")
    suspend fun getMaterialAndTrans(MaterialId:String): List<MaterialAndTrans>

    //getUserWithTransactions
    @Transaction
    @Query("SELECT * FROM user_table WHERE userId = :userId")
    suspend fun getUserAndTrans(userId:String): List<UserAndTrans>


}


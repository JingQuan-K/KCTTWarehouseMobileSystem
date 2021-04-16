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

    @Query("SELECT EXISTS (SELECT * FROM material_table WHERE MaterialId = :MaterialId)")
    fun materialExists(MaterialId: Int): Boolean

    //RACK
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRack(rack: Rack)

    @Query("SELECT RackId FROM rack_table")
    suspend fun  getAllRacks():List<String>

    @Query("SELECT * FROM rack_table WHERE RackId = :RackId")
    fun getRack(RackId: String) : Rack

    @Query("SELECT Quantity FROM rack_table WHERE RackId = :RackId")
    fun getRackQuantity(RackId: String) : Int

    @Query("UPDATE rack_table SET Quantity = :Quantity WHERE RackId = :RackId")
    fun setRackQuantity(Quantity: Int, RackId: String)

    @Query("SELECT EXISTS (SELECT * FROM rack_table WHERE RackId = :RackId)")
    fun rackExists(RackId: String): Boolean

    @Transaction
    @Query("SELECT rack_table.RackId FROM rack_table, material_table WHERE rack_table.MaterialId = material_table.MaterialId AND MaterialName LIKE '%' || :MaterialName || '%'")
    suspend fun getRacksOfMaterial(MaterialName: String): List<String>

    @Transaction
    @Query("SELECT RackId FROM rack_table WHERE MaterialId = :MaterialId")
    fun getRackOfSpecificMaterial(MaterialId: Int) : List<String>




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


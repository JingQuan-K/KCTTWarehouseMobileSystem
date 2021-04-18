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

    @Query("SELECT * FROM material_table WHERE MaterialTypeId = :MaterialTypeId AND Quantity > reorderLvl ORDER BY MaterialId ")
    suspend fun getMTIDMaterial(MaterialTypeId: Int): List<Material>

    @Query("SELECT * FROM material_table WHERE MaterialTypeId = :MaterialTypeId AND Quantity <= reorderLvl ORDER BY MaterialId ")
    suspend fun getMTIDMaterialLow(MaterialTypeId: Int): List<Material>

    @Query("UPDATE material_table SET MaterialName = :MaterialName, Quantity = :Quantity, CostPI = :CostPI, totalValue = :totalValue, reorderLvl = :reorderLvl")
    suspend fun updateMaterial(MaterialName: String, Quantity: Int, CostPI:Double, totalValue: Double, reorderLvl: Int)

    @Query("SELECT * FROM rack_table ORDER BY RackId DESC")
    fun getAllRack(): LiveData<List<Rack>>

    @Query("SELECT * FROM transaction_table ORDER BY TransactionId ASC")
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

    @Query("SELECT MaterialId FROM material_table ORDER BY MaterialId DESC LIMIT 1")
    suspend fun getLastMId(): Int

    @Query("SELECT * FROM material_table")
    suspend fun getListMaterial(): List<Material>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransaction(transactions: Transactions)

    @Query("SELECT * FROM material_table WHERE MaterialId = :MaterialId")
    fun getMaterial(MaterialId: Int) : Material

    @Query("SELECT EXISTS (SELECT * FROM material_table WHERE MaterialId = :MaterialId)")
    fun materialExists(MaterialId: Int): Boolean

    @Query("SELECT CostPI FROM material_table WHERE MaterialId = :MaterialId")
    suspend fun getMaterialCostPerItem(MaterialId: Int) :Double

    @Query("UPDATE material_table SET totalValue = :totalValue WHERE MaterialId = :MaterialId")
    suspend fun setMaterialTotalValue(totalValue: Double, MaterialId: Int)

    @Query("SELECT Quantity FROM material_table WHERE MaterialId = :MaterialId")
    suspend fun getMaterialQuantity(MaterialId: Int) : Int

    @Query("UPDATE material_table SET Quantity = :Quantity WHERE MaterialId = :MaterialId")
    suspend fun setMaterialQuantity(Quantity: Int, MaterialId: Int)

    @Query("SELECT RackId FROM rack_table")
    suspend fun  getAllRacks():List<String>

    @Query("SELECT * FROM rack_table WHERE RackId = :RackId")
    fun getRack(RackId: String) : Rack

    @Query("SELECT Quantity FROM rack_table WHERE RackId = :RackId")
    suspend fun getRackQuantity(RackId: String) : Int

    @Query("SELECT Quantity FROM rack_table WHERE RackId = :RackId")
    fun getRQty(RackId: String) : Int

    @Query("UPDATE rack_table SET Quantity = :Quantity WHERE RackId = :RackId")
    suspend fun setRackQuantity(Quantity: Int, RackId: String)

    @Query("SELECT EXISTS (SELECT * FROM rack_table WHERE RackId = :RackId)")
    fun rackExists(RackId: String): Boolean

    @Query("UPDATE rack_table SET MaterialId = :MaterialId WHERE RackId = :RackId")
    suspend fun setRackMaterialId(MaterialId: Int, RackId: String)

    @Transaction
    @Query("SELECT rack_table.RackId FROM rack_table, material_table WHERE rack_table.MaterialId = material_table.MaterialId AND MaterialName LIKE '%' || :MaterialName || '%'")
    suspend fun getRacksOfMaterial(MaterialName: String): List<String>

    @Transaction
    @Query("SELECT RackId FROM rack_table WHERE MaterialId = :MaterialId")
    fun getRackOfSpecificMaterial(MaterialId: Int) : List<String>


    //getTypeWithMaterials
    @Transaction
    @Query("SELECT * FROM materialType_table WHERE MaterialTypeId = :MaterialTypeId ")
    suspend fun getTypeWithMaterials(MaterialTypeId:String): List<MaterialAndType>

    //getMaterialWithTransactions
    @Transaction
    @Query("SELECT * FROM material_table WHERE MaterialId = :MaterialId")
    suspend fun getMaterialWithTransactions(MaterialId:String): List<MaterialAndTrans>

    //getUserWithTransactions
    @Transaction
    @Query("SELECT * FROM user_table WHERE userId = :userId")
    suspend fun getUserWithTransactions(userId:String): List<UserAndTrans>

    //IMAGE
    @Query("SELECT Image FROM material_table WHERE MaterialId = :MaterialId")
    suspend fun getImageOfMaterial(MaterialId: Int): ByteArray
}



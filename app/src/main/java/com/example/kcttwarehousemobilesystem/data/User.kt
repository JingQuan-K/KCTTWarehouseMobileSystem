package com.example.kcttwarehousemobilesystem.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.text.DecimalFormat

@Entity(tableName = "user_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        val userId: Int,
        val username: String,
        val password: String,
        val email: String
)
@Entity(tableName = "materialType_table")
data class MaterialType(
        @PrimaryKey(autoGenerate = true)
        val MaterialTypeID: Int,
        val MaterialTypeName: String
)

data class Material(
        @PrimaryKey(autoGenerate = true)
        val MaterialID: Int,
        val MaterialName: String,
        val MaterialPhotoPath: String,
        val Quantity: Int,
        val CostPI: Double,
        val totalValue: Double,
        val reorderLvl: Int,
)


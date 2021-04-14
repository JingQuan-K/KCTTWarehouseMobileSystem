package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "material_table")
data class Material(
    @PrimaryKey(autoGenerate = true ) val MaterialId: Int,
    val MaterialName: String,
    val MaterialPhotoPath: String,
    val Quantity: Int,
    val CostPI: Double,
    val totalValue: Double,
    val reorderLvl: Int,
    val MaterialTypeId: Int
)
package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materialType_table")
data class MaterialType(
    @PrimaryKey(autoGenerate = true)val MaterialTypeId: Int,
    val MaterialTypeName: String
)
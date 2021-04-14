package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class MaterialAndType(
    @Embedded val materialType: MaterialType,
    @Relation(
        parentColumn = "MaterialTypeId",
        entityColumn = "MaterialTypeId"
    )
    val material: List<Material>
)
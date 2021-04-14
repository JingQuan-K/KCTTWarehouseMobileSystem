package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Embedded
import androidx.room.Relation

//Material Type with materials
data class MaterialAndType(
    @Embedded val materialType: MaterialType,
    @Relation(
        parentColumn = "MaterialTypeId",
        entityColumn = "MaterialTypeId"
    )
    val material: List<Material>
)
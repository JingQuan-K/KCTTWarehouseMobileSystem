package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class MaterialAndRack(
    @Embedded val material: Material,
    @Relation(
        parentColumn = "MaterialId",
        entityColumn = "MaterialId"
    )
    val rack: List<Rack>
)
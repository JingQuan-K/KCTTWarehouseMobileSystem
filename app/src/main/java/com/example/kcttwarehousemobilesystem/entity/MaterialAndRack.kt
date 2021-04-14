package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Embedded
import androidx.room.Relation

//Material on racks
data class MaterialAndRack(
    @Embedded val material: Material,
    @Relation(
        parentColumn = "MaterialId",
        entityColumn = "MaterialId"
    )
    val rack: List<Rack>
)
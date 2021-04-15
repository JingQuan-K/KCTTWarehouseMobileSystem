package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class MaterialAndTrans(
    @Embedded val material: Material,
    @Relation(
        parentColumn = "MaterialId",
        entityColumn = "MaterialId"
    )
    val transactions: List<Transactions>
)
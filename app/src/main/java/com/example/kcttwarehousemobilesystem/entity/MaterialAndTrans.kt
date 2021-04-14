package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Embedded
import androidx.room.Relation

//Materials with Transaction
data class MaterialAndTrans(
    @Embedded val material: Material,
    @Relation(
        parentColumn = "MaterialId",
        entityColumn = "MaterialId"
    )
    val transactions: List<Transactions>
)
package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transactions(
    @PrimaryKey(autoGenerate = true)
    val TransactionId: Int,
    val TransactionType: String,
    val Quantity: Int,
    val MaterialId: Int,
)
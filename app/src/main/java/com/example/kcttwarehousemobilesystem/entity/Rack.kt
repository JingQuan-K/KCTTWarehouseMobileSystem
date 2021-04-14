package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rack_table")
data class Rack(
    @PrimaryKey(autoGenerate = false )
    val RackId: String,
    val MaterialId: Int
)
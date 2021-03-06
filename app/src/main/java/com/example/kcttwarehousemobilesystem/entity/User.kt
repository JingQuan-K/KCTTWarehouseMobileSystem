package com.example.kcttwarehousemobilesystem.entity

import androidx.room.*

@Entity(tableName = "user_table")
data class User(
        @PrimaryKey val userId: Int,
        val username: String,
        val password: String,
        val email: String
)


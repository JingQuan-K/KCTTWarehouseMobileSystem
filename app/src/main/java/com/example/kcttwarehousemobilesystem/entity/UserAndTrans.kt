package com.example.kcttwarehousemobilesystem.entity

import androidx.room.Embedded
import androidx.room.Relation

//User with transactions
data class UserAndTrans(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val transaction: List<Transactions>
)
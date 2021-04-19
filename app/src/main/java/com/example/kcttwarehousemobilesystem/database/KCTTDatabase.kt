package com.example.kcttwarehousemobilesystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kcttwarehousemobilesystem.entity.Material
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import com.example.kcttwarehousemobilesystem.entity.Rack
import com.example.kcttwarehousemobilesystem.entity.Transactions

@Database(entities = [Material::class, MaterialType::class, Rack::class, Transactions::class], version = 1, exportSchema = false )

abstract class KCTTDatabase : RoomDatabase() {

    abstract fun userDao(): KCTTDao

    companion object {
        @Volatile
        private var INSTANCE: KCTTDatabase? = null

        fun getDatabase(context: Context): KCTTDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KCTTDatabase::class.java,
                    "user_database"

                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
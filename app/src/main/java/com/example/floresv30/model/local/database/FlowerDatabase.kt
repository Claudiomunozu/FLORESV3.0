package com.example.floresv30.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.floresv30.model.local.dao.FlowerDao
import com.example.floresv30.model.local.entities.FlowerDetailLocal
import com.example.floresv30.model.local.entities.FlowerLocal

@Database(entities = [FlowerLocal::class, FlowerDetailLocal::class], version = 1, exportSchema = false)

abstract class FlowerDatabase : RoomDatabase() {
    abstract fun getFlowerDao(): FlowerDao

    companion object{

        @Volatile
        private var INSTANCE : FlowerDatabase? = null

        fun getDataBase(context: Context): FlowerDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlowerDatabase::class.java,
                    "flower_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
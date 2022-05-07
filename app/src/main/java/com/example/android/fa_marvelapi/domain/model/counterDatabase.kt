package com.example.android.fa_marvelapi.domain.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.fa_marvelapi.domain.repository.CounterDAO

@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class counterDatabase: RoomDatabase() {
    abstract fun counterDao():CounterDAO

    companion object{
        @Volatile
        private var INSTANCE: counterDatabase? = null

        fun getDatabase(context: Context): counterDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    counterDatabase::class.java,
                    "counter_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
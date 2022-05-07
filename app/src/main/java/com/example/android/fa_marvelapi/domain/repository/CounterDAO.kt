package com.example.android.fa_marvelapi.domain.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.fa_marvelapi.domain.model.Entity

@Dao
interface CounterDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCounter(entity: Entity)

    //suspend fun incrementCounter(entity: Entity)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Entity>>
}
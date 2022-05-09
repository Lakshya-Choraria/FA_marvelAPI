package com.example.android.fa_marvelapi.domain.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.fa_marvelapi.domain.model.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCounter(entity: Entity)

    @Update
    suspend fun incrementCounter(entity: Entity)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Entity>>

    @Query("SELECT * FROM user_table WHERE charactername LIKE :searchQuery")
    fun searchDatabase(searchQuery: String) : Entity
}
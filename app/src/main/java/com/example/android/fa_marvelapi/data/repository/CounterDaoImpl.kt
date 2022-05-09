package com.example.android.fa_marvelapi.data.repository

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import com.example.android.fa_marvelapi.domain.model.Entity
import com.example.android.fa_marvelapi.domain.repository.CounterDAO
import kotlinx.coroutines.flow.Flow

class CounterDaoImpl(private val counterDAO: CounterDAO) {
    val readAllData: LiveData<List<Entity>> = counterDAO.readAllData()

    suspend fun addCounter(entity: Entity){
        counterDAO.addCounter(entity)
    }
    suspend fun incrementCounter(entity: Entity){
        counterDAO.incrementCounter(entity)
    }
    fun searchDatabase(searchQuery: String): Entity{
        return counterDAO.searchDatabase(searchQuery)
    }
}
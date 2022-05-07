package com.example.android.fa_marvelapi.data.repository

import androidx.lifecycle.LiveData
import com.example.android.fa_marvelapi.domain.model.Entity
import com.example.android.fa_marvelapi.domain.repository.CounterDAO

class CounterDaoImpl(private val counterDAO: CounterDAO) {
    val readlAllData: LiveData<List<Entity>> = counterDAO.readAllData()

    suspend fun addCounter(entity: Entity){
        counterDAO.addCounter(entity)
    }
}
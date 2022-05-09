package com.example.android.fa_marvelapi.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.fa_marvelapi.data.repository.CounterDaoImpl
import com.example.android.fa_marvelapi.domain.model.Entity
import com.example.android.fa_marvelapi.domain.model.counterDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CounterViewModel(application: Application): AndroidViewModel(application) {

    val readallData: LiveData<List<Entity>>
    private val repository: CounterDaoImpl
    init{
        val counterDAO = counterDatabase.getDatabase((application)).counterDao()
        repository = CounterDaoImpl(counterDAO)
        readallData = repository.readAllData
    }

    fun addCounter(entity: Entity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCounter(entity)
        }
    }
    fun incrementCounter(entity: Entity){
        viewModelScope.launch(Dispatchers.IO){
            val newEntity = Entity(entity.id,entity.charactername,entity.counter+1)
            repository.incrementCounter(newEntity)
        }
    }
    fun searchDatabase(searchQuery: String): Entity {
        return repository.searchDatabase(searchQuery)
    }
}
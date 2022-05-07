package com.example.android.fa_marvelapi.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.fa_marvelapi.data.repository.CounterDaoImpl
import com.example.android.fa_marvelapi.domain.model.Entity
import com.example.android.fa_marvelapi.domain.model.counterDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CounterViewModel(application: Application): AndroidViewModel(application) {

    val readallData: LiveData<List<Entity>>
    private val repository: CounterDaoImpl
    init{
        val counterDAO = counterDatabase.getDatabase((application)).counterDao()
        repository = CounterDaoImpl(counterDAO)
        readallData = repository.readlAllData
    }

    fun addCounter(entity: Entity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCounter(entity)
        }
    }
}
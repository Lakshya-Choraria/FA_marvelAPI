package com.example.android.fa_marvelapi.UI.CharacterList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.fa_marvelapi.domain.use_cases.CharacterUseCases
import com.example.android.fa_marvelapi.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCases
): ViewModel(){
    private val marvelvalue = MutableStateFlow(ListState())
    var _marvelValue : StateFlow<ListState> = marvelvalue

    fun getAllCharactersData(offset:Int) = viewModelScope.launch(Dispatchers.IO) {
        characterUseCase(offset=offset).collect {
            when (it){
                is Response.Success -> {
                    marvelvalue.value = ListState(characterList = it.data?: emptyList())
                    Log.d("toCharacter",_marvelValue.value.toString())
                }
                is Response.Loading -> {
                    marvelvalue.value = ListState(isLoading = true)
                    Log.d("loading",_marvelValue.value.toString())
                }
                 is Response.Error -> {
                     marvelvalue.value = ListState(error=it.message?:"Error Occurred")
                     Log.d("error",_marvelValue.value.toString())
                }
            }
        }
    }
}


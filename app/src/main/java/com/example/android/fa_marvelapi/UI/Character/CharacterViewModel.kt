package com.example.android.fa_marvelapi.UI.Character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.fa_marvelapi.domain.use_cases.IndCharUseCase
import com.example.android.fa_marvelapi.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: IndCharUseCase
) : ViewModel(){

    private val characterValue = MutableStateFlow(CharacterState())
    val _characterValue : StateFlow<CharacterState> = characterValue

    fun getCharacterByIdValue(id:String)=viewModelScope.launch{
        characterUseCase(id).collect {
            when(it){
                is Response.Success-> {
                    characterValue.value = CharacterState(
                        characterDetail = it.data?: emptyList()
                    )
                }
                is Response.Loading->{
                    characterValue.value = CharacterState(isLoading = true)
                }
                is Response.Error->{
                    characterValue.value = CharacterState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
}
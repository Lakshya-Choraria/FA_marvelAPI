package com.example.android.fa_marvelapi.UI.Character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.fa_marvelapi.domain.model.Character
import com.example.android.fa_marvelapi.domain.use_cases.CharacterUseCases
import com.example.android.fa_marvelapi.domain.use_cases.IndCharUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCases
) : ViewModel(){

    private val characterValue = MutableStateFlow(CharacterState())
    val _characterValue : StateFlow<CharacterState> = characterValue

    fun getCharacterByIdValue(id:String)=viewModelScope.launch{
        IndCharUseCase(id).collect {
            when(it){
                is State.Success-> {
                    characterValue.value = CharacterState(
                        characterDetail = it.data?: emptyList()
                    )
                }
                is State.Loading->{
                    characterValue.value = CharacterState(isLoading = true)
                }
                is State.Error->{
                    characterValue.value = CharacterState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
}
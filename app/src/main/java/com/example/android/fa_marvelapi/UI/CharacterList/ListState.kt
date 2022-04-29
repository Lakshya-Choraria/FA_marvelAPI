package com.example.android.fa_marvelapi.UI.CharacterList

import com.example.android.fa_marvelapi.domain.model.Character

data class ListState(
    val isLoading : Boolean = false,
    val characterList : List<Character> = emptyList(),
    val error : String = ""
)

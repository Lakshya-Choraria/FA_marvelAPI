package com.example.android.fa_marvelapi.UI.Character

import com.example.android.fa_marvelapi.domain.model.Character

class CharacterState (
    val isLoading : Boolean = false,
    val characterDetail : List<Character> = emptyList(),
    val error : String = ""
)
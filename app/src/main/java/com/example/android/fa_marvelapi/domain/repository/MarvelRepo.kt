package com.example.android.fa_marvelapi.domain.repository

import com.example.android.fa_marvelapi.data.data_source.dto.CharacterDTO

interface MarvelRepo {
    suspend fun getAllCharacter(offset:Int): CharacterDTO
    suspend fun getAllSearchedCharacters(search: String):CharacterDTO
    suspend fun getCharacterById(id:String): CharacterDTO
}
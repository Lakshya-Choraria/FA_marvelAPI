package com.example.android.fa_marvelapi.data.repository

import com.example.android.fa_marvelapi.data.data_source.MarvelAPI
import com.example.android.fa_marvelapi.data.data_source.dto.CharacterDTO
import com.example.android.fa_marvelapi.domain.repository.MarvelRepo
import javax.inject.Inject

class MarvelRepoImplementation @Inject constructor(
    private val api:MarvelAPI
) : MarvelRepo {
    override suspend fun getAllCharacter(offset: Int): CharacterDTO {
        return api.getALLCharacters(offset = offset.toString())
    }

    override suspend fun getAllSearchedCharacters(search: String): CharacterDTO {
        return api.getAllSearchedCharacters(search=search)
    }

    override suspend fun getCharacterById(id: String): CharacterDTO {
        return api.getCharacterById(id)
    }
}
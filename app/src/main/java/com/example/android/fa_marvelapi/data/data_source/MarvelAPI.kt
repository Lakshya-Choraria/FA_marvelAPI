package com.example.android.fa_marvelapi.data.data_source

import com.example.android.fa_marvelapi.data.data_source.dto.CharacterDTO
import com.example.android.fa_marvelapi.domain.model.Character
import com.example.android.fa_marvelapi.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {
    @GET("/v1/public/characters")
    suspend fun getALLCharacters(
        @Query("apikey")apikey:String = Constants.API_KEY,
        @Query("ts")ts:String = Constants.timeStamp,
        @Query("hash")hash:String = Constants.hash(),
        @Query("offset")offset:String
    ):CharacterDTO

    @GET("/v1/public/characters")
    suspend fun  getAllSearchedCharacters(
        @Query("apikey")apikey:String = Constants.API_KEY,
        @Query("ts")ts:String = Constants.timeStamp,
        @Query("hash")hash:String = Constants.hash(),
        @Query("nameStartsWith")search:String
    ): CharacterDTO

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId")characterId:String,
        @Query("ts")ts:String=Constants.timeStamp,
        @Query("apikey")apikey:String=Constants.API_KEY,
        @Query("hash")hash:String=Constants.hash(),
    ):CharacterDTO

}
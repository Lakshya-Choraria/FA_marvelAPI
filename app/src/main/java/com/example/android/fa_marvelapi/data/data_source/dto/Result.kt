package com.example.android.fa_marvelapi.data.data_source.dto

import com.example.android.fa_marvelapi.domain.model.Character

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
){
    fun toCharacter():Character{
        return Character(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail.path,
            thumbnailExit = thumbnail.extension,
            comics = comics.items.map {
                it.name
            }
        )
    }
}
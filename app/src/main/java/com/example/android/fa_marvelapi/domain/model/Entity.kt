package com.example.android.fa_marvelapi.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "click_counter")
data class Entity(
    @PrimaryKey (autoGenerate = true)
    val charactername: String,
    val counter:Int
)

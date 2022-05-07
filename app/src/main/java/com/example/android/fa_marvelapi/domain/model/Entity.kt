package com.example.android.fa_marvelapi.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Entity(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val charactername: String,
    val counter:Int
)

package com.example.android.fa_marvelapi.data.data_source.dto

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
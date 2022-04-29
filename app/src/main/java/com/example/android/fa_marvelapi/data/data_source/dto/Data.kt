package com.example.android.fa_marvelapi.data.data_source.dto

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)
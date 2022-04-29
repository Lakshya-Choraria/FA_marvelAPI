package com.example.android.fa_marvelapi.Depinject

import android.provider.SyncStateContract
import com.example.android.fa_marvelapi.data.data_source.MarvelAPI
import com.example.android.fa_marvelapi.data.repository.MarvelRepoImplementation
import com.example.android.fa_marvelapi.domain.repository.MarvelRepo
import com.example.android.fa_marvelapi.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object injecter {

    @Provides
    @Singleton
    fun provideMarvelApi(): MarvelAPI{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(MarvelAPI::class.java)

    }
    @Provides
    @Singleton
    fun provideMarvelRepository(api:MarvelAPI):MarvelRepo{
        return MarvelRepoImplementation(api)
    }
}
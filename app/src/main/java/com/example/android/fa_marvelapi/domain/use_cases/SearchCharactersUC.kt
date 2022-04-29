package com.example.android.fa_marvelapi.domain.use_cases

import com.example.android.fa_marvelapi.domain.model.Character
import com.example.android.fa_marvelapi.domain.repository.MarvelRepo
import com.example.android.fa_marvelapi.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchCharactersUC @Inject constructor(
    private val repository: MarvelRepo
) {
    operator fun invoke(search: String): Flow<Response<List<Character>>> = flow {
        try {
            emit(Response.Loading<List<Character>>())
            val list = repository.getAllSearchedCharacters(search).data.results.map {
                it.toCharacter()
            }
            emit(Response.Success<List<Character>>(list))
        }
        catch (e:HttpException){
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
        catch (e:IOException){
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
    }
}
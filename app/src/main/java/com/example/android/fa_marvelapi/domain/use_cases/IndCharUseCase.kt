package com.example.android.fa_marvelapi.domain.use_cases

import com.example.android.fa_marvelapi.domain.model.Character
import com.example.android.fa_marvelapi.domain.repository.MarvelRepo
import com.example.android.fa_marvelapi.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class IndCharUseCase @Inject constructor(
    private val repository : MarvelRepo
) {
    operator fun invoke(id:String): Flow<Response<List<Character>>> = flow {
        try {
            emit(Response.Loading<List<Character>>())
            val character = repository.getCharacterById(id).data.results.map {
                it.toCharacter()
            }
            emit(Response.Success<List<Character>>(character))
        }
        catch (e: HttpException){
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
        catch (e: IOException){
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
    }
}
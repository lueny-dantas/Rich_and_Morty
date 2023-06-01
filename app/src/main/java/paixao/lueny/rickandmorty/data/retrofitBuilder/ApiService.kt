package paixao.lueny.rickandmorty.data.retrofitBuilder

import paixao.lueny.rickandmorty.data.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("api/character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharactersResponse

}
package paixao.lueny.rickandmorty.data.retrofitBuilder

import paixao.lueny.rickandmorty.data.model.ApiCharactersResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("api/character")
    suspend fun getCharacters(@QueryMap params: Map<String, String> = emptyMap()): ApiCharactersResponse

}
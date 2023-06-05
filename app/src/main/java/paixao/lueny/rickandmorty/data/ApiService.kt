package paixao.lueny.rickandmorty.data

import paixao.lueny.rickandmorty.data.model.CharacterResponse
import paixao.lueny.rickandmorty.data.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    @GET("api/character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @QueryMap params: Map<String, String>
    ): CharactersResponse

    @GET("api/character/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: Int): CharacterResponse

}
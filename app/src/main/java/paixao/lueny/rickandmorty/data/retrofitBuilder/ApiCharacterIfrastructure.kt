package paixao.lueny.rickandmorty.data.retrofitBuilder

import paixao.lueny.rickandmorty.data.mappers.CharactersMapper
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.domain.models.Page
import paixao.lueny.rickandmorty.domain.models.PageInfo


class ApiCharacterInfrastructure() {
    private val api get() = RetrofitBuilder().create(ApiService::class.java)

    suspend fun getCharacters(page: Int): Page {
        val params = mutableMapOf<String, String>()
        val actualPage = mapOf("page" to page.toString())

        params.putAll(actualPage)

        val response = api.getCharacters(params)

        val characters: MutableList<Character> = mutableListOf()


        for (characterResponse in response.results) {
            val character = CharactersMapper.toDomain(characterResponse)
            characters.add(character)
        }

        return Page(
            characters = characters,
            info = PageInfo(response.info.pages, response.info.next)
        )


    }

}

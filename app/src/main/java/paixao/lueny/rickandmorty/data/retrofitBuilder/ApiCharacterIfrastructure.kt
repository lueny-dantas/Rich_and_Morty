package paixao.lueny.rickandmorty.data.retrofitBuilder

import paixao.lueny.rickandmorty.data.mappers.CharactersMapper
import paixao.lueny.rickandmorty.domain.models.Character


class ApiCharacterInfrastructure() {
    private val api get() = RetrofitBuilder().create(ApiService::class.java)

    suspend fun getCharacters(): List<Character> {
        val characterResponse = api.getCharacters().results
        val characters: MutableList<Character> = mutableListOf()

        for (characterResponse in characterResponse) {
            val character = CharactersMapper.toDomain(characterResponse)
            characters.add(character)
        }
        return characters
    }
}

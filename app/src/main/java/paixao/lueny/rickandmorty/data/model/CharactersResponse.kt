package paixao.lueny.rickandmorty.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val results: List<CharacterResponse>? = listOf()
)

@Serializable
data class CharacterResponse(
    val id: Int = 0,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val origin: OriginResponse,
    val location: LocationResponse,
    val status: String,
)

@Serializable
data class OriginResponse(
    val name: String
)

@Serializable
data class LocationResponse(
    val name: String
)



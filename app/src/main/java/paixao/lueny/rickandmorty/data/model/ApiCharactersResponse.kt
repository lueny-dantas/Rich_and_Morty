package paixao.lueny.rickandmorty.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiCharactersResponse(
    val info: PageInfoResponse,
    val results: List<CharacterResponse>
)

@Serializable
data class PageInfoResponse(
    val pages: Int,
    val next: String? = null,
)
@Serializable
data class CharacterResponse(
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


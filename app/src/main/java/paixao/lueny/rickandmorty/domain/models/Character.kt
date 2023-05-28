package paixao.lueny.rickandmorty.domain.models

import paixao.lueny.rickandmorty.ui.uiState.CharactersListUiState


data class Page(
    val info: PageInfo,
    val characters: List<Character>
)

data class PageInfo(
    val totalPages: Int,
    val nextPage: String?,
)
data class Character(
    val name: String,
    val status: Status,
    val species: String,
    val gender: Gender,
    val origin: Origin,
    val location: Location,
    val image: String,
) {
    enum class Status(val statusPresentation: String) {
        Alive("Vivo"),
        Dead("Morto"),
        Unknown("Desconhecido")
    }

    enum class Gender(val genderApresentation: String) {
        Female("Fêmea"),
        Male("Macho"),
        Genderless("Sem Gênero"),
        Unknown("Desconhecido")
    }

    data class Origin(
        val name: String
    )

    data class Location(
        val name: String
    )
}

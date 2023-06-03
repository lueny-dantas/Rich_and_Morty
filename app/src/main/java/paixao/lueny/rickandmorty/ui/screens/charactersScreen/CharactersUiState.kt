package paixao.lueny.rickandmorty.ui.screens.charactersScreen

import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.domain.models.Page
import paixao.lueny.rickandmorty.domain.models.PageInfo

data class CharactersUiState(
    val paginationState: PaginationState = PaginationState(),
    val characters: List<Character> = emptyList(),
    val onFilterClick: (String?, Character.Status?) -> Unit
)

data class PaginationState(
    val currentPage: Int = 0,
    val lastPage: Page = Page(
        info = PageInfo(
            nextPage = null,
            totalPages = 999
        ),
        characters = emptyList()
    ),
)

package paixao.lueny.rickandmorty.ui.uiState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.domain.models.Page
import paixao.lueny.rickandmorty.domain.models.PageInfo

data class CharactersListUiState(
    val paginationState: PaginationState = PaginationState(),
    val characters: List<Character> = emptyList()
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

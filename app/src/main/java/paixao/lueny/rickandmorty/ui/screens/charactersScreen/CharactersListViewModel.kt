package paixao.lueny.rickandmorty.ui.screens.charactersScreen

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import paixao.lueny.rickandmorty.data.retrofitBuilder.ApiCharacterInfrastructure
import paixao.lueny.rickandmorty.ui.uiState.CharactersListUiState
import paixao.lueny.rickandmorty.ui.uiState.PaginationState

class CharactersListViewModel() {

    val service = ApiCharacterInfrastructure()

    private val _uiState = MutableStateFlow(CharactersListUiState())
    val uiState = _uiState.asStateFlow()

    suspend fun getCharacters(nextPage: Int) {
        val lastState = _uiState.value
        val page = if (lastState.paginationState.lastPage.info.totalPages > nextPage || nextPage == 1)
            service.getCharacters(nextPage)
        else
            null

        if (page != null) {
            val newPaginationState = PaginationState(
                currentPage = nextPage,
                lastPage = page,
            )

            val newCharactersList = _uiState.value.characters + page.characters

            val newState = CharactersListUiState(
                paginationState = newPaginationState,
                characters = newCharactersList
            )

            _uiState.emit(newState)
        }
    }
}
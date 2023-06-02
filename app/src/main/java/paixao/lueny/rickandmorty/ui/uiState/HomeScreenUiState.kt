package paixao.lueny.rickandmorty.ui.uiState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

data class HomeScreenUiState (
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {})


/*
sealed interface HomeScreenUiState {
    data class Success(val characters: String) : HomeScreenUiState
    data class Loading : HomeScreenUiState
    data class Error : HomeScreenUiState
}

val _uiState: HomeScreenUiState by mutableStateOf(HomeScreenUiState.Loading)
    private set

private fun getCharacters(){

}
*/



package paixao.lueny.rickandmorty.ui.uiState

import paixao.lueny.rickandmorty.model.Character

data class CharactersUiState(
    val characters: List<Character> = emptyList()
)
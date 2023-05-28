package paixao.lueny.rickandmorty.ui.uiState

import paixao.lueny.rickandmorty.domain.models.Character

data class CharactersUiState(
    val characters: List<Character> = emptyList()
)
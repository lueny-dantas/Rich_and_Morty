package paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import paixao.lueny.rickandmorty.data.CharactersDataSource
import paixao.lueny.rickandmorty.domain.models.Character

class CharacterDetailsViewModel() : ViewModel() {

    //CRIAR UM CharactersDataSource aqui, junto com variaveis de StateFlow e MutableStateFlow

    private val _character = MutableStateFlow<Character?>(null)
    val character = _character.asStateFlow()

    val characterDataSource = CharactersDataSource()
    suspend fun getCharacter(characterId: Int) {
        val character = characterDataSource.getCharacter(characterId = characterId)
        _character.emit(character)
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Context
                Log.i("CharacterDetailsVM", "context: $context")
                CharacterDetailsViewModel()
            }
        }
    }
}
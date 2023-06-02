package paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class CharacterDetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Context
                Log.i("CharacterDetailsVM", "context: $context")
                CharacterDetailsViewModel(
                    savedStateHandle = createSavedStateHandle()
                )
            }
        }
    }
}
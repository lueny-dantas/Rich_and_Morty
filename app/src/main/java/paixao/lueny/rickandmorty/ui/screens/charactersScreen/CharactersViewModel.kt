package paixao.lueny.rickandmorty.ui.screens.charactersScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import paixao.lueny.rickandmorty.data.CharactersDataSource
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen.CharacterDetailsViewModel

class CharactersViewModel(): ViewModel() {

    private val charactersDataSource = CharactersDataSource()
    fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 999),
            pagingSourceFactory = { charactersDataSource }
        ).flow.cachedIn(viewModelScope)
        }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Context
                Log.i("CharacterDetailsVM", "context: $context")
                CharactersViewModel()
            }
        }
    }

    }
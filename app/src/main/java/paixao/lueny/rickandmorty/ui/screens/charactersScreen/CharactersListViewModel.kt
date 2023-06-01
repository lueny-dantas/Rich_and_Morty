package paixao.lueny.rickandmorty.ui.screens.charactersScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import paixao.lueny.rickandmorty.data.model.CharactersResponse
import paixao.lueny.rickandmorty.data.retrofitBuilder.ApiService
import paixao.lueny.rickandmorty.data.retrofitBuilder.CharacterDataSource
import paixao.lueny.rickandmorty.domain.models.Character

class CharactersListViewModel(): ViewModel() {

    private val charactersDataSource = CharacterDataSource()
    fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 999),
            pagingSourceFactory = { charactersDataSource }
        ).flow.cachedIn(viewModelScope)
        }
    }